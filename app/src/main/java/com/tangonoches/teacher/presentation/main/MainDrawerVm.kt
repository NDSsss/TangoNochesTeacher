package com.tangonoches.teacher.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tangonoches.teacher.domain.datasources.prefs.IPrefsStorage
import com.tangonoches.teacher.presentation.base.BaseVm
import javax.inject.Inject

class MainDrawerVm @Inject constructor(
    private val prefsStorage: IPrefsStorage
): BaseVm(){
    private val _email = MutableLiveData<String>().apply {
        value = prefsStorage.teacherEmail
    }
    val email: LiveData<String> = _email
}