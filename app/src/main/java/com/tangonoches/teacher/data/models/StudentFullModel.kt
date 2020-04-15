package com.tangonoches.teacher.data.models

import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID

data class StudentFullModel(
    val barcodeId: Long = DEFAULT_ID,
    val createdAt: String = "",
    val extraInfo: String = "",
    val facebookProfileId: Long = DEFAULT_ID,
    val facebookProfileLink: String = "",
    val id: Long = DEFAULT_ID,
    val instagramProfileId: Long = DEFAULT_ID,
    val instagramProfileLink: String = "",
    val name: String = "",
    val phone: String = "",
    val photoLink: String = "",
    val pushToken: String = "",
    val updatedAt: String = "",
    val vkProfileId: Long = DEFAULT_ID,
    val vkProfilelink: String = "",
    val points: Int = 0
)