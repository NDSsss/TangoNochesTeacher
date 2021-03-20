package ru.nds.core.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseVmActivity<VM : BaseVm>(
    vmClass: KClass<VM>
) : AppCompatActivity() {

    protected abstract val layoutId: Int

    protected val vm: VM by viewModel(clazz = vmClass)

    protected val vmBinds: CompositeDisposable = CompositeDisposable()

    protected val eventBinds: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        vm.viewCreated()
        initEvents()
    }

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

    protected fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        eventBinds.clear()
        super.onDestroy()
    }
}