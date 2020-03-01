package com.tangonoches.teacher

import androidx.multidex.MultiDexApplication
import com.tangonoches.teacher.di.ComponentsHolder
import com.tangonoches.teacher.di.components.DaggerMainComponent

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        ComponentsHolder.mainComponent = DaggerMainComponent.builder().context(this).build()
    }
}