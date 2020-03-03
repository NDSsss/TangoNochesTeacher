package com.tangonoches.teacher.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tangonoches.teacher.di.vm.VmFactory
import com.tangonoches.teacher.di.vm.VmKeyName
import com.tangonoches.teacher.presentation.login.LoginVm
import com.tangonoches.teacher.presentation.main.MainDrawerVm
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonsVm
import com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail.LessonDetailVm
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface VmModule {

    @Binds
    @IntoMap
    @VmKeyName(LoginVm::class)
    fun bindLoginAvtivityVm(viewModel: LoginVm): ViewModel

    @Binds
    @IntoMap
    @VmKeyName(LessonsVm::class)
    fun bindLessonsFragmentVm(viewModel: LessonsVm): ViewModel

    @Binds
    @IntoMap
    @VmKeyName(MainDrawerVm::class)
    fun bindMainDrawerActivityFragmentVm(viewModel: MainDrawerVm): ViewModel

    @Binds
    @IntoMap
    @VmKeyName(LessonDetailVm::class)
    fun bindLessonDetailVmVm(viewModel: LessonDetailVm): ViewModel

    @Binds
    @Singleton
    fun provideViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory

}