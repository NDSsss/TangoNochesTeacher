package ru.nds.common.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.nds.core.domain.prefs.IPrefsStorage
import ru.nds.core.domain.prefs.PrefsStorage


val prefsModule = module {
    factory<IPrefsStorage> {
        PrefsStorage(
            context = androidContext()
        )
    }
}