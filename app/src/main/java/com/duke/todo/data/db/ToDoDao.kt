package com.duke.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.duke.todo.data.db.entity.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_tbl ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>


    @Insert
    suspend fun insertData(toDoData: ToDoData)


    @Update
    suspend fun updateData(toDoData: ToDoData)


    @Delete
    suspend fun deleteSingleItem(toDoData: ToDoData)

    @Query("DELETE FROM todo_tbl")
    suspend fun deleteAllItem()


    @Query("SELECT * FROM todo_tbl WHERE title LIKE :search")
    fun searchQueryInDb(search: String): LiveData<List<ToDoData>>


}