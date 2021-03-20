package com.tangonoches.teacher.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nds.core.domain.prefs.IPrefsStorage
import ru.nds.core.presentation.base.BaseVm

class MainDrawerVm(
    private val prefsStorage: IPrefsStorage
) : BaseVm() {
    private val _email = MutableLiveData<String>().apply {
        value = prefsStorage.teacherEmail
    }
    val email: LiveData<String> = _email
}