package ru.nds.core.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.nds.core.di.utils.vm.VmFactory
import javax.inject.Singleton

@Module
interface CoreVmModule {
    @Binds
    @Singleton
    fun provideViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}