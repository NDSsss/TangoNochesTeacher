package ru.nds.core.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.CompositeDisposable
import ru.nds.core.di.utils.vm.VmFactoryWrapper

abstract class BaseVmActivity<VM : BaseVm> : AppCompatActivity() {

    protected abstract val layoutId: Int

    protected val vmFactoryWrapper = VmFactoryWrapper()

    protected val vm: VM by lazy {
        ViewModelProviders
            .of(this, vmFactoryWrapper.factory)
            .get(getVmClass())
    }

    protected val vmBinds: CompositeDisposable = CompositeDisposable()

    protected val eventBinds: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
//        ComponentsHolder.mainComponent.inject(vmFactoryWrapper)
        injectWrapper()
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        vm.viewCreated()
        initEvents()
    }

    abstract fun injectWrapper()

    open protected fun initEvents() {

    }

    open protected fun createVmBinds() {
        vmBinds.add(
            vm.errorRelay.subscribe {
                showError(it)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        createVmBinds()
    }

    override fun onPause() {
        vmBinds.clear()
        super.onPause()
    }

    protected fun getVmFactory(): ViewModelProvider.Factory = vmFactoryWrapper.factory

    protected abstract fun getVmClass(): Class<VM>

    protected fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        eventBinds.clear()
        super.onDestroy()
    }
}