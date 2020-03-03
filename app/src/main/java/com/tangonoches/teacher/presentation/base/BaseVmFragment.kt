package com.tangonoches.teacher.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tangonoches.teacher.di.ComponentsHolder
import com.tangonoches.teacher.di.VmFactoryWrapper
import io.reactivex.disposables.CompositeDisposable

abstract class BaseVmFragment<VM : BaseVm> : Fragment() {

    protected abstract val layoutId: Int

    protected val vmFactoryWrapper = VmFactoryWrapper()

    protected val vm: VM by lazy {
        ViewModelProviders
            .of(this, vmFactoryWrapper.factory)
            .get(getVmClass())
    }

    protected abstract fun getVmClass(): Class<VM>

    protected val vmBinds: CompositeDisposable = CompositeDisposable()

    protected val eventBinds: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentsHolder.mainComponent.inject(vmFactoryWrapper)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.viewCreated()
        initEvents()
        vm.viewCreatedAction.accept(Unit)
    }


    override fun onResume() {
        super.onResume()
        createVmBinds()
    }

    override fun onPause() {
        vmBinds.clear()
        super.onPause()
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

    protected fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        eventBinds.clear()
        super.onDestroyView()
    }

    protected fun openFragment(@IdRes id: Int, bundle:Bundle? = null){
        if(bundle != null){
            findNavController().navigate(id, bundle)
        }else {
            findNavController().navigate(id)
        }
    }
}