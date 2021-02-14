package com.duke.todo.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.duke.todo.data.db.MYDatabase
import com.duke.todo.data.db.entity.ToDoData
import com.duke.todo.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoDao = MYDatabase.getDataBase(application).toDao()
    private val repository: ToDoRepository
    private val geAllData: LiveData<List<ToDoData>>


    init {
        repository = ToDoRepository(todoDao)
        geAllData = repository.getAllData
    }


    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

}