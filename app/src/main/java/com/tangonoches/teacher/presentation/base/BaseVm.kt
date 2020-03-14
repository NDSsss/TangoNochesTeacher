package com.tangonoches.teacher.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseVm : ViewModel() {

    init {
        Log.d("lifecycle", "${this::class.java.simpleName} init")
    }

    protected val binds = CompositeDisposable()

    val errorRelay = BehaviorRelay.create<String>()

    val closeRelay = PublishRelay.create<Unit>()

    val loadingState = BehaviorRelay.create<Boolean>()

    val showToastAction = PublishRelay.create<String>()

    var firstStart: Boolean = true

    public fun viewCreated() {
        if (firstStart) {
            onFirstStart()
            firstStart = false
        }
        createBinds()
    }

    open fun onFirstStart() {

    }

    open protected fun createBinds() {
        Log.d("lifecycle", "${this::class.java.simpleName} createBinds")
        binds.addAll(
            errorRelay.subscribe {
                loadingState.accept(false)
            }
        )
    }

    override fun onCleared() {
        Log.d("lifecycle", "${this::class.java.simpleName} onCleared")
        binds.clear()
        super.onCleared()
    }

    protected fun startLoading() {
        loadingState.accept(true)
    }

    protected fun completeLoading() {
        loadingState.accept(false)
    }

    protected fun showError(th: Throwable) {
        errorRelay.accept(th.message ?: "Error")
    }

    protected fun showToast(message: String) {
        showToastAction.accept(message)
    }

    protected fun close() {
        closeRelay.accept(Unit)
    }

    fun <T : Any> Single<T>.subLoading(): Single<T> = this
        .doOnSubscribe { startLoading() }
        .doOnSuccess { completeLoading() }
        .doOnError { showError(it) }

    fun <T : Any> Single<T>.subWithDefaultError(success: (T) -> Unit): Disposable =
        this.subscribe(
            { result ->
                success(result)
            },
            {
                showError(it)
            }
        )

    fun Completable.subLoading(): Completable = this
        .doOnSubscribe { startLoading() }
        .doOnComplete { completeLoading() }
        .doOnError { showError(it) }

    fun Completable.subWithDefaultError(success: () -> Unit): Disposable =
        this.subscribe(
            {
                success()
            },
            {
                showError(it)
            }
        )
}

