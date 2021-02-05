package com.tangonoches.teacher.presentation.view.base

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import io.reactivex.disposables.CompositeDisposable

@Suppress("LeakingThis")
abstract class BasesWidget<VM : BaseWidgetVm> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    lateinit var vm: VM
    var firstStart: Boolean = true
    protected val rxBinds: CompositeDisposable = CompositeDisposable()

    init {
        inflate(context)
    }

    protected abstract fun inflate(context: Context)

    protected open fun injects() {}

    open fun initVm(vm: VM, firstStart: Boolean) {
        Log.d("lifecycle", "${this::class.java.simpleName} initVm isFirstStart $firstStart")
        injects()
        this.vm = vm
        this.firstStart = firstStart
        if (firstStart) {
            vm.viewCreated()
        }
        createVmBinds()
    }

    open protected fun createVmBinds() {

    }

    override fun onDetachedFromWindow() {
        Log.d("lifecycle", "${this::class.java.simpleName} onDetachedFromWindow")
        super.onDetachedFromWindow()
        rxBinds.clear()
    }
}