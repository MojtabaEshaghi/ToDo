package com.duke.todo.data.db

import android.content.Context
import androidx.room.*
import com.duke.todo.data.db.entity.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(PrioritiesConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun toDao(): ToDoDao



}