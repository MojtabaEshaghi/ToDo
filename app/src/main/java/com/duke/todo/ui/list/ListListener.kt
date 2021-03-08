package com.duke.todo.ui.list



interface ListListener {



    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String?)



}