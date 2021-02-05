package ru.nds.common.di.module

import android.content.Context
import ru.nds.core.domain.prefs.IPrefsStorage
import ru.nds.core.domain.prefs.PrefsStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefsModule {
    @Provides
    @Singleton
    fun providePrefsStorage(context: Context): IPrefsStorage =
        PrefsStorage(context)
}