package com.duke.todo.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duke.todo.data.db.entity.Priorities
import com.duke.todo.data.db.entity.ToDoData
import com.duke.todo.data.repository.ToDoRepository
import com.duke.todo.ui.add.AddListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(private val repository: ToDoRepository) :
    ViewModel() {

    var addListener: AddListener? = null
    var title: String? = null
    var description: String? = null
    var priorites: String? = null

    fun insertData() {
        addListener?.onStarted()

        if ((title.isNullOrEmpty()) || (description.isNullOrEmpty()) || (priorites.isNullOrEmpty())) {
            addListener?.onFailure("all fields is requires")

        } else {
            val data = ToDoData(
                0,
                title!!,
                description!!,
                parsePriority(priorites!!)
            )

            viewModelScope.launch {
                repository.insertData(data)
                addListener?.onSuccess()


            }
        }


    }

    private fun parsePriority(inputs: String): Priorities {
        return when (inputs) {

            "High Priorities" -> Priorities.HIGH
            "Medium Priorities" -> Priorities.MEDIUM
            "Low Priorities" -> Priorities.LOW
            else -> Priorities.LOW

        }

    }


}