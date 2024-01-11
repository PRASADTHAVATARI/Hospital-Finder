package com.example.healthspotapp

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast


class Comfun(val context:Context) {

    val p by lazy{
    Dialog(context).apply {
        setContentView(R.layout.dialog)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
    fun anytoast(any :Any?){
        Toast.makeText(context, "$any", Toast.LENGTH_SHORT).show()
    }
}