package com.duke.todo.utils

import android.content.Context
import android.widget.Toast
import com.valdesekamdem.library.mdtoast.MDToast

fun eToast(msg: String, context: Context) {


    val md: MDToast = MDToast.makeText(context, msg, Toast.LENGTH_SHORT, MDToast.TYPE_ERROR)

    md.show()

}



fun sToast(msg: String, context: Context) {


    val md: MDToast = MDToast.makeText(context, msg, Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS)

    md.show()

}