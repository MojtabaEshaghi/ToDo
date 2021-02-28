package com.duke.todo.data.viewModel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duke.todo.R
import com.duke.todo.data.db.entity.Priorities
import com.duke.todo.data.db.entity.ToDoData
import com.duke.todo.data.repository.ToDoRepository
import com.duke.todo.ui.add.AddListener
import com.duke.todo.ui.list.ListListener
import com.duke.todo.ui.update.UpdateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(private val repository: ToDoRepository) :
    ViewModel() {

    var addListener: AddListener? = null
    var listListener: ListListener? = null
    var updateListener: UpdateListener? = null
    private val TAG = "jojo"
    var title: String? = null
    var description: String? = null
    var priorites: String? = null


    fun insertData() {
        addListener?.onStarted()

        if ((title.isNullOrEmpty()) || (description.isNullOrEmpty()) || (priorites.isNullOrEmpty())) {
            addListener?.onFailure(" تمامی فیلدها باید پر بشوند")

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

    fun getAllData() {
        listListener?.onStarted()

        val res: LiveData<List<ToDoData>> = repository.getAllData
        listListener?.onSuccess(res)

    }


    fun updateTodo(toDoData: ToDoData) {

        updateListener?.onStarted()


        if (toDoData.title.isEmpty() || toDoData.description.isEmpty()) {
            updateListener?.onFailure("you must be enter some data")

        } else {
            Log.i(TAG, "updateTodo: " + toDoData.title)
            Log.i(TAG, "updateTodo: " + toDoData.priorities)
            Log.i(TAG, "updateTodo: " + toDoData.description)
            Log.i(TAG, "updateTodo: " + toDoData.id)


            viewModelScope.launch {

                repository.updateData(toDoData)
                updateListener?.onSuccess()
            }


        }


    }


    fun deleteSingleItem(toDoData: ToDoData) {

        updateListener?.onStarted()

        try {
            viewModelScope.launch {
                repository.deleteSingleItem(toDoData)
                updateListener?.onSuccess()
            }
        } catch (e: Exception) {
            updateListener?.onFailure("someThing Wrong")
        }


    }


    fun isDbEmpty(list: List<ToDoData>) {
        Log.i(TAG, "isDbEmpty: "+list.isNullOrEmpty())

    }


    fun deleteAllItem() {
        listListener?.onStarted()


        try {
            viewModelScope.launch {
                repository.deleteAllItem()
                listListener?.onSuccessDeletedAll()
            }
        } catch (e: Exception) {
            listListener?.onFailure()
        }


    }


    fun parsePriority(inputs: String): Priorities {
        return when (inputs) {

            "High Priorities" -> Priorities.HIGH
            "Medium Priorities" -> Priorities.MEDIUM
            "Low Priorities" -> Priorities.LOW
            else -> Priorities.LOW

        }

    }


    val SpinnerListener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                when (position) {

                    0 -> {
                        (parent?.getChildAt(0) as TextView).setTextColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.red
                            )
                        )
                    }
                    1 -> {
                        (parent?.getChildAt(0) as TextView).setTextColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.yellow
                            )
                        )
                    }
                    2 -> {
                        (parent?.getChildAt(0) as TextView).setTextColor(
                            ContextCompat.getColor(
                                parent.context,
                                R.color.green
                            )
                        )
                    }

                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }


    fun parsePriority(prioritiy: Priorities): Int {

        return when (prioritiy) {
            Priorities.LOW -> 2
            Priorities.MEDIUM -> 1
            Priorities.HIGH -> 0
        }


    }


}