package com.tangonoches.teacher.domain.extensions

import android.widget.TextView

fun TextView.setTextIfNotEqual(textToSet:String){
    if(this.text.toString().contentEquals(textToSet).not()){
        this.text = textToSet
    }
}