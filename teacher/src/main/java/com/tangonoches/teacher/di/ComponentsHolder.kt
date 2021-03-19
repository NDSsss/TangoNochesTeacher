package com.tangonoches.teacher.di

import com.tangonoches.teacher.di.components.DaggerMainComponent
import com.tangonoches.teacher.di.components.MainComponent
import ru.nds.common.di.RefreshableComponentHolder
import ru.nds.teacher.network.di.TeacherNetworkComponentHolder

object ComponentsHolder : RefreshableComponentHolder {
    private var _mainComponent: MainComponent? = null
    val mainComponent: MainComponent
        get() {
            if (_mainComponent == null) {
                _mainComponent = DaggerMainComponent.builder()
                    .teacherNetworkComponent(
                        TeacherNetworkComponentHolder.getComponentAndRegisterRefresh(
                            this
                        )
                    )
                    .build()
            }
            return _mainComponent!!
        }

    override fun refresh() {
        _mainComponent = null
    }
}