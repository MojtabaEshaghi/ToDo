package com.duke.todo.ui.list

import androidx.lifecycle.LiveData
import com.duke.todo.data.db.entity.ToDoData

interface ListListener {



    fun onStarted()
    fun onSuccess(res: LiveData<List<ToDoData>>)
    fun onFailure()
    fun onSuccessDeletedAll()


}