package com.duke.todo.utils

import android.content.Context
import android.widget.Toast

fun sToast(msg: String,context: Context) {

    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

}