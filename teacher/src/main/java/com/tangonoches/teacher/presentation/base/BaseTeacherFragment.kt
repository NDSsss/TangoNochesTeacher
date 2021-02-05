package com.tangonoches.teacher.presentation.base

import com.tangonoches.teacher.di.ComponentsHolder
import ru.nds.core.presentation.base.BaseVm
import ru.nds.core.presentation.base.BaseVmFragment

abstract class BaseTeacherFragment<VM: BaseVm>: BaseVmFragment<VM>() {
    override fun injectWrapper() {
        ComponentsHolder.mainComponent.inject(vmFactoryWrapper)
    }
}