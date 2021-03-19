package ru.nds.teacher.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.nds.core.di.utils.vm.VmFactory
import ru.nds.core.di.utils.vm.VmKeyName
import ru.nds.teacher.di.TeacherLoginScope
import ru.nds.teacher.presentation.login.LoginVm

@Module
internal interface VmModule {

    @Binds
    @IntoMap
    @VmKeyName(LoginVm::class)
    @TeacherLoginScope
    fun bindLoginVm(loginVm: LoginVm): ViewModel

    @Binds
    @TeacherLoginScope
    fun provideViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}