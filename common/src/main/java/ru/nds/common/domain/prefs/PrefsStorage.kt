package ru.nds.core.domain.prefs

import android.content.Context

const val PREFS_NAME = "TOKEN_PREFS"
const val TEACHER_TOKEN = "TEACHER_TOKEN"
const val TEACHER_EMAIL = "TEACHER_MAIL"

internal class PrefsStorage(private val context: Context) : IPrefsStorage {
    override var teacherToken: String
        get() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.getString(
            TEACHER_TOKEN,
            ""
        ) ?: ""
        set(value) {
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.edit()
                ?.putString(TEACHER_TOKEN, value)?.apply()
        }
    override var teacherEmail: String
        get() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.getString(
            TEACHER_EMAIL,
            ""
        ) ?: ""
        set(value) {
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.edit()
                ?.putString(TEACHER_EMAIL, value)?.apply()
        }

}