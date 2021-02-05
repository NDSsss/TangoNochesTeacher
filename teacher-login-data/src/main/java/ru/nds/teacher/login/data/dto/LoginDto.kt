package ru.nds.teacher.login.data.dto

import ru.nds.teacher.login.domain.entity.LoginEntity

data class LoginDto(val api_token: String) {
    fun toEntity(): LoginEntity = LoginEntity(apiToken = api_token)
}