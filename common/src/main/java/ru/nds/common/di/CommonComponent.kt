package ru.nds.common.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import ru.nds.common.di.module.CommonModule
import ru.nds.common.di.module.PrefsModule
import ru.nds.common.navigation.CoordinatorProvider
import ru.nds.common.rx.ISchedulers
import ru.nds.core.domain.prefs.IPrefsStorage
import javax.inject.Singleton

@Component(
    modules = [CommonModule::class, PrefsModule::class]
)
@Singleton
interface CommonComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CommonComponent
    }

    fun provideContext(): Context
    fun provideSchedulers(): ISchedulers
    fun provideCoordinatorProvider(): CoordinatorProvider
    fun provideIPrefsStorage(): IPrefsStorage
}