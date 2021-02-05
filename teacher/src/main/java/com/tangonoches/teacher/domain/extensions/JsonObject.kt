package com.tangonoches.teacher.domain.extensions

import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import org.json.JSONObject

fun JSONObject.putIdIfNotDefault(key: String, id: Long) {
    if (id != DEFAULT_ID) {
        this.put(key, id)
    }
}

fun JSONObject.putNullIfEmpty(key: String, value: String) {
    if (value.isEmpty()) {
        this.put(key, null)
    } else {
        this.put(key,value)
    }
}