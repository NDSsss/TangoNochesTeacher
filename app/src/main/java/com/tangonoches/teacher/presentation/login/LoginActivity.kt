package com.tangonoches.teacher.presentation.login

import android.content.Intent
import com.jakewharton.rxbinding2.view.clicks
import com.tangonoches.teacher.MainActivity
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseVmActivity
import com.tangonoches.teacher.presentation.main.MainDrawerActivity
import kotlinx.android.synthetic.main.act_login.*

class LoginActivity : BaseVmActivity<LoginVm>() {
    override fun getVmClass(): Class<LoginVm> =
        LoginVm::class.java

    override val layoutId: Int = R.layout.act_login

    override fun initEvents() {
        super.initEvents()
        eventBinds.add(
            act_login_btn_login.clicks().subscribe {
                vm.loginEvent.accept(
                    act_login_et_email.text.toString() to act_login_et_password.text.toString()
                )
            }
        )
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            vm.loginSuccessState.subscribe {
                loginSuccess()
            }
        )
    }

    private fun loginSuccess() {
        startActivity(Intent(this, MainDrawerActivity::class.java))
        finish()
    }

}