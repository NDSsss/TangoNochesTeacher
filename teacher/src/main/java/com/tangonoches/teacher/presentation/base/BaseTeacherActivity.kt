package com.tangonoches.teacher.presentation.base

import com.tangonoches.teacher.di.ComponentsHolder
import ru.nds.core.presentation.base.BaseVm
import ru.nds.core.presentation.base.BaseVmActivity

abstract class BaseTeacherActivity<VM: BaseVm>: BaseVmActivity<VM>() {
    override fun injectWrapper() {
        ComponentsHolder.mainComponent.inject(vmFactoryWrapper)
    }
}