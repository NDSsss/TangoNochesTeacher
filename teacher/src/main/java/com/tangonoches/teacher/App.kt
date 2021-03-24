package com.tangonoches.teacher

import androidx.multidex.MultiDexApplication
import com.tangonoches.teacher.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import ru.nds.common.di.commonModule
import ru.nds.common.di.prefsModule
import ru.nds.teacher.di.module.teacherLoginPresentationVmModule
import ru.nds.teacher.network.di.module.netModule
import ru.nds.teacher.network.di.module.teacherInterceptorModule

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initKoin()wwwww
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
        }
        loadKoinModules(commonModule)
        loadKoinModules(prefsModule)
        loadKoinModules(teacherInterceptorModule)
        loadKoinModules(netModule)
        loadKoinModules(teacherLoginPresentationVmModule)
        loadKoinModules(monolitVmModule)
        loadKoinModules(monoliteEditorsModule)
        loadKoinModules(monolitInteractorsModule)
        loadKoinModules(monolitRepositoriesModule)
        loadKoinModules(monolitServicesModule)
        loadKoinModules(monolitWebDataSourceModule)
    }
}