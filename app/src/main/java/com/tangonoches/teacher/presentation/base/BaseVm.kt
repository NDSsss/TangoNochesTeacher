package com.tangonoches.teacher.presentation.base

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.R
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseVm : ViewModel() {

    init {
        Log.d("lifecycle", "${this::class.java.simpleName} init")
    }

    protected val binds = CompositeDisposable()

    val errorRelay = PublishRelay.create<String>()

    val closeRelay = PublishRelay.create<Unit>()

    val loadingState = BehaviorRelay.create<Boolean>()

    val showToastAction = PublishRelay.create<String>()

    val showDialogAction = PublishRelay.create<ShowDialogModel>()

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
        Log.d("lifecycle", "${this::class.java.simpleName} startLoading")
        loadingState.accept(true)
    }

    protected fun completeLoading() {
        Log.d("lifecycle", "${this::class.java.simpleName} completeLoading")
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

    protected fun showDialog(model: ShowDialogModel) {
        showDialogAction.accept(model)
    }

    fun <T : Any> BehaviorRelay<T>.getValueOrThrowNPE(): T =
        this.value ?: throw NullPointerException("Relay value can not bu null")

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

    fun <T : Any> Observable<T>.subWithDefaultError(success: (T) -> Unit): Disposable =
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

data class ShowDialogModel(
    val message: Int = R.string.dialog_default_message,
    val positiveButtonRes :Int? = null,
    val neutralButtonRes :Int? = null,
    val negativeButtonRes :Int? = null,
    val positiveAction: () -> Unit = {},
    val neutralAction: () -> Unit = {},
    val negativeAction: () -> Unit = {}
)

