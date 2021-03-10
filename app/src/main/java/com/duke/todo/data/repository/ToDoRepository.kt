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


    suspend fun updateData(toDoData: ToDoData) {
        toDoDao.updateData(toDoData)
    }


    suspend fun deleteSingleItem(toDoData: ToDoData) {
        toDoDao.deleteSingleItem(toDoData)
    }


    suspend fun deleteAllItem() {
        toDoDao.deleteAllItem()
    }

    fun searchInDb(searchString: String): LiveData<List<ToDoData>> {
        return toDoDao.searchQueryInDb(searchString)
    }

}