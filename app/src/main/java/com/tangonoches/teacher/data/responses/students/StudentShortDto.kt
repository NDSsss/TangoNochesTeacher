package com.tangonoches.teacher.data.responses.students

import com.tangonoches.teacher.data.models.StudentShortModel

data class StudentShortDto(
    val barcode_id: Long = 0,
    val id: Long = 0,
    val name: String = "",
    val phone: String? = ""
)

fun StudentShortDto.toModel(): StudentShortModel =
    StudentShortModel(
        barcodeId = barcode_id,
        name = name,
        id = id,
        phone = phone?:""
    )