package ru.nds.teacher.presentation.login

import android.content.Intent
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.teach_act_login.*
import ru.nds.core.presentation.base.BaseVmActivity
import ru.nds.teacher.BuildConfig
import ru.nds.teacher.R
import ru.nds.teacher.di.TeacherLoginComponentHolder

class LoginActivity : BaseVmActivity<LoginVm>() {
    override fun getVmClass(): Class<LoginVm> =
        LoginVm::class.java

    override fun injectWrapper() {
        TeacherLoginComponentHolder.teacherLoginComponent.inject(vmFactoryWrapper)
    }

    override val layoutId: Int = R.layout.teach_act_login

    override fun initEvents() {
        super.initEvents()
        eventBinds.add(
            teacher_act_login_btn_login.clicks().subscribe {
                vm.loginEvent.accept(
                    teacher_act_login_et_email.text.toString() to teacher_act_login_et_password.text.toString()
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
        if (BuildConfig.DEBUG) {
            teacher_act_login_et_email.setText("noname@mail.ru")
            teacher_act_login_et_password.setText("12345678")
        }
    }

    private fun loginSuccess() {
        finish()
    }

}