package ru.nds.common.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.nds.common.navigation.CoordinatorProvider
import ru.nds.common.navigation.CoordinatorProviderImpl
import ru.nds.common.rx.ISchedulers
import ru.nds.common.rx.ISchedulersImpl
import javax.inject.Singleton

@Module
internal class CommonModule {
    @Singleton
    @Provides
    internal fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    internal fun provideSchedulers(): ISchedulers = ISchedulersImpl()

    @Singleton
    @Provides
    internal fun provideCoordinatorProvider(): CoordinatorProvider = CoordinatorProviderImpl()
}