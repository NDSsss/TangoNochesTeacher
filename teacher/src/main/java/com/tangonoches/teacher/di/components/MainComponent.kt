package com.tangonoches.teacher.di.components

import com.tangonoches.teacher.di.TeacherScope
import com.tangonoches.teacher.di.modules.*
import dagger.Component
import ru.nds.common.di.CommonComponent
import ru.nds.core.di.utils.vm.VmFactoryWrapper
import ru.nds.teacher.network.di.TeacherNetworkComponent

@Component(
    dependencies = [
        TeacherNetworkComponent::class
    ],
    modules = [
        WebDataSourcesModule::class,
        RepositoriesModule::class,
        ServicesModule::class,
        InteractorsModule::class,
        EditorsModule::class,
        VmModule::class
    ]
)
@TeacherScope
interface MainComponent {
    fun inject(vmFactoryWrapper: VmFactoryWrapper)
}