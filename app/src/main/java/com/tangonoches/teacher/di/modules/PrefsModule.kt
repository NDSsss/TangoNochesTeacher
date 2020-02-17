package com.tangonoches.teacher.di.modules

import android.content.Context
import com.tangonoches.teacher.domain.datasources.prefs.IPrefsStorage
import com.tangonoches.teacher.domain.datasources.prefs.PrefsStorage
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