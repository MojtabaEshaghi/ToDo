package com.duke.todo.data.viewModel

import android.content.Context
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duke.todo.R
import com.duke.todo.data.db.entity.Priorities
import com.duke.todo.data.db.entity.ToDoData
import com.duke.todo.data.repository.ToDoRepository
import com.duke.todo.ui.add.AddListener
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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

    private fun parsePriority(inputs: String): Priorities {
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

}