package ru.nds.teacher.di

import ru.nds.common.di.RefreshableComponentHolder
import ru.nds.teacher.di.component.DaggerTeacherLoginComponent
import ru.nds.teacher.di.component.TeacherLoginComponent
import ru.nds.teacher.network.di.TeacherNetworkComponentHolder

object TeacherLoginComponentHolder : RefreshableComponentHolder {
    private var _teacherLoginComponent: TeacherLoginComponent? = null

    val teacherLoginComponent: TeacherLoginComponent
        get() {
            if (_teacherLoginComponent == null) {
                _teacherLoginComponent = DaggerTeacherLoginComponent.builder()
                    .teacherNetworkComponent(
                        TeacherNetworkComponentHolder.getComponentAndRegisterRefresh(
                            this
                        )
                    )
                    .build()
            }
            return _teacherLoginComponent!!
        }

    override fun refresh() {
        _teacherLoginComponent = null
    }
}