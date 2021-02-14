package com.duke.todo.data.db

import android.content.Context
import androidx.room.*
import com.duke.todo.data.db.entity.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(PrioritiesConverter::class)
abstract class MYDatabase : RoomDatabase() {

    abstract fun toDao(): ToDoDao


    companion object {
        @Volatile
        private var INSTANCE: MYDatabase? = null

        fun getDataBase(context: Context): MYDatabase {
            val tmpInstance = INSTANCE
            if (tmpInstance != null) {
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MYDatabase::class.java,
                    "todo_db"
                ).build()


                INSTANCE = instance
                return instance

            }


        }

    }


}