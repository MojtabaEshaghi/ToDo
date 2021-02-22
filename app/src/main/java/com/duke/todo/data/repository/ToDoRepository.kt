package com.duke.todo.data.repository

import androidx.lifecycle.LiveData
import com.duke.todo.data.db.ToDoDao
import com.duke.todo.data.db.entity.ToDoData
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {


    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()


    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }


}