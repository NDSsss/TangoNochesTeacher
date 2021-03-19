package com.tangonoches.teacher

import androidx.multidex.MultiDexApplication
import com.tangonoches.teacher.di.ComponentsHolder
import com.tangonoches.teacher.di.components.DaggerMainComponent
import ru.nds.common.di.CommonComponentHolder
import ru.nds.common.di.DaggerCommonComponent
import ru.nds.teacher.network.di.DaggerTeacherNetworkComponent
import ru.nds.teacher.network.di.TeacherNetworkComponentHolder

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    private fun initComponents() {
        CommonComponentHolder.commonComponent =
            DaggerCommonComponent.builder().application(this).build()
        TeacherNetworkComponentHolder.init()
    }
}