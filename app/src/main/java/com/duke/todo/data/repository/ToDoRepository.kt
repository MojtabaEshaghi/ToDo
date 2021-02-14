package com.duke.todo.data.repository

import androidx.lifecycle.LiveData
import com.duke.todo.data.db.ToDoDao
import com.duke.todo.data.db.entity.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {


    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()


    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }


}