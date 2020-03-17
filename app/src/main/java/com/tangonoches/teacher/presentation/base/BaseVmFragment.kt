package com.tangonoches.teacher.presentation.base

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tangonoches.teacher.R
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

    protected var firstStart = true
    protected var firstViewCreate = true

    protected abstract fun getVmClass(): Class<VM>

    protected val vmBinds: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("lifecycle", "${this::class.java.simpleName} onCreate")
        ComponentsHolder.mainComponent.inject(vmFactoryWrapper)
        preCreateVmBinds()
        vm.viewCreated()
        super.onCreate(savedInstanceState)
        firstStart = false
    }

    protected open fun preCreateVmBinds() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("lifecycle", "${this::class.java.simpleName} onCreateView")
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("lifecycle", "${this::class.java.simpleName} onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onResume() {
        firstViewCreate = false
        Log.d("lifecycle", "${this::class.java.simpleName} onResume")
        super.onResume()
        createVmBinds()
    }

    override fun onPause() {
        Log.d("lifecycle", "${this::class.java.simpleName} onPause")
        hideKeyboard()
        vmBinds.clear()
        super.onPause()
    }


    open protected fun createVmBinds() {
        Log.d("lifecycle", "${this::class.java.simpleName} createVmBinds")
        vmBinds.addAll(
            vm.errorRelay.subscribe {
                showError(it)
            },
            vm.closeRelay.subscribe {
                back()
            },
            vm.showToastAction.subscribe { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            },
            vm.showDialogAction.subscribe { dialogModel ->
                showDialog(dialogModel)
            },
            vm.loadingState.subscribe {
                if (it) {
                    startLoading()
                } else {
                    completeLoading()
                }
            }
        )
    }

    protected fun showDialog(dialogModel: ShowDialogModel) {
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.AppDialog)
        dialogBuilder.setMessage(dialogModel.message)
        dialogModel.positiveButtonRes?.let { positiveRes ->
            dialogBuilder.setPositiveButton(positiveRes) { dialog: DialogInterface, p2 ->
                dialogModel.positiveAction()
                dialog.dismiss()
            }
        }
        dialogModel.neutralButtonRes?.let { neutralRes ->
            dialogBuilder.setNegativeButton(neutralRes) { dialog: DialogInterface, p2 ->
                dialogModel.neutralAction()
                dialog.dismiss()
            }
        }
        dialogModel.negativeButtonRes?.let { negativeRes ->
            dialogBuilder.setNegativeButton(negativeRes) { dialog: DialogInterface, p2 ->
                dialogModel.negativeAction()
                dialog.dismiss()
            }
        }
        dialogBuilder.show()
    }

    protected fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        Log.d("lifecycle", "${this::class.java.simpleName} onDestroyView")
        super.onDestroyView()
    }

    protected fun openFragment(@IdRes id: Int, bundle: Bundle? = null) {
        if (bundle != null) {
            findNavController().navigate(id, bundle)
        } else {
            findNavController().navigate(id)
        }
    }

    protected fun back() {
        findNavController().navigateUp()
    }

    protected open fun startLoading() {
        Log.d("lifecycle", "${this::class.java.simpleName} startLoading")
        (activity as? IActivityInterface).let { act ->
            act?.startLoading()
        }
    }

    protected open fun completeLoading() {
        Log.d("lifecycle", "${this::class.java.simpleName} completeLoading")
        (activity as? IActivityInterface).let { act ->
            act?.completeLiading()
        }
    }

    protected fun hideKeyboard() {
        (activity as? IActivityInterface).let { act ->
            act?.hideKeyboard()
        }
    }
}