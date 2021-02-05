package ru.nds.teacher.presentation.model

import ru.nds.teacher.login.domain.entity.LoginEntity

data class LoginModel(val apiToken: String)

fun LoginEntity.toModel(): LoginModel = LoginModel(apiToken = apiToken)