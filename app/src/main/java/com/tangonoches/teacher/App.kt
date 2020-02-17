package com.tangonoches.teacher

import android.app.Application
import com.tangonoches.teacher.di.ComponentsHolder
import com.tangonoches.teacher.di.components.DaggerMainComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentsHolder.mainComponent = DaggerMainComponent.builder().context(this).build()
    }
}