package com.tangonoches.teacher.di.components

import android.content.Context
import com.tangonoches.teacher.di.VmFactoryWrapper
import com.tangonoches.teacher.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        InterceptorsModule::class,
        NetModule::class,
        PrefsModule::class,
        WebDataSourcesModule::class,
        RepositoriesModule::class,
        ServicesModule::class,
        InteractorsModule::class,
        EditorsModule::class,
        VmModule::class
    ]
)
@Singleton
interface MainComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): MainComponent
    }

    fun inject(vmFactoryWrapper: VmFactoryWrapper)
}