package ru.nds.teacher.di.component

import dagger.Component
import ru.nds.core.di.utils.vm.VmFactoryWrapper
import ru.nds.teacher.di.TeacherLoginScope
import ru.nds.teacher.di.module.DataModule
import ru.nds.teacher.di.module.DomainModule
import ru.nds.teacher.di.module.VmModule
import ru.nds.teacher.network.di.TeacherNetworkComponent
import ru.nds.teacher.presentation.login.LoginActivity

@Component(
    dependencies = [
        TeacherNetworkComponent::class
    ],
    modules = [
        DataModule::class,
        DomainModule::class,
        VmModule::class
    ]
)
@TeacherLoginScope
interface TeacherLoginComponent {
    fun injectLoginActivity(activity: LoginActivity)
    fun inject(vmFactoryWrapper: VmFactoryWrapper)
}