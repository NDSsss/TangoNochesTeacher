package com.tangonoches.teacher.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable

abstract class BaseVm : ViewModel() {
    protected val binds = CompositeDisposable()

    val errorRelay = BehaviorRelay.create<String>()

    val viewCreatedAction = PublishRelay.create<Unit>()

    public fun viewCreated(){
        createBinds()
    }

    open protected fun createBinds(){

    }

    override fun onCleared() {
        binds.clear()
        super.onCleared()
    }
}