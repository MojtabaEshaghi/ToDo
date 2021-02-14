package com.duke.todo.data.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.duke.todo.data.db.entity.Priorities

class PrioritiesConverter {


    @TypeConverter
    fun fromPriority(priorities: Priorities):String{
        return priorities.name
    }
    

    @TypeConverter
    fun toPriority(priorities:String):Priorities{
        return Priorities.valueOf(priorities)
    }
    
}