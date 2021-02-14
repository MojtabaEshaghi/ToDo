package com.duke.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.duke.todo.data.db.entity.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_tbl ORDER BY id ASC")
    fun getAllData():LiveData<List<ToDoData>>



    @Insert
    suspend fun insertData(toDoData: ToDoData)








}