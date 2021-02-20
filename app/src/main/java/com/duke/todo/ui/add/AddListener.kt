package com.duke.todo.ui.add

interface AddListener {


    fun onStarted()
    fun onSuccess()
    fun onFailure(msg: String)


}