package com.duke.todo.ui.update

interface UpdateListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(s: String)


}