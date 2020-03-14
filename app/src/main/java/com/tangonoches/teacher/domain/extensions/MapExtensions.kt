package com.tangonoches.teacher.domain.extensions

import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID

fun HashMap<String,Any?>.putIfNotDefault(key:String, id: Long){
    if(id != DEFAULT_ID){
        this[key] = id
    }
}fun HashMap<String,Any?>.putNullIfEmpty(key:String, value: String){
    if(value.isEmpty()){
        this[key] = null
    }else {
        this[key] = value
    }
}