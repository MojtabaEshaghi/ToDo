package com.duke.todo.di.module

import android.content.Context
import androidx.room.Room
import com.duke.todo.data.db.MyDatabase
import com.duke.todo.data.db.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {


    @Singleton
    @Provides
    fun providerDb(@ApplicationContext context: Context): MyDatabase {

        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "todo"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    }


    @Singleton
    @Provides
    fun providerToDoDao(roomDatabase: MyDatabase): ToDoDao {

        return roomDatabase.toDao()

    }


}