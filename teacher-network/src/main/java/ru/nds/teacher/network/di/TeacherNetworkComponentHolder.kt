package ru.nds.teacher.network.di

import ru.nds.common.di.CommonComponentHolder
import ru.nds.common.di.RefreshableComponentHolder

object TeacherNetworkComponentHolder : RefreshableComponentHolder {
    private val componentsToRefresh: MutableList<RefreshableComponentHolder> = mutableListOf()
    private var _teacherNetworkComponent: TeacherNetworkComponent? = null
    val teacherNetworkComponent: TeacherNetworkComponent
        get() {
            if (_teacherNetworkComponent == null) {
                init()
            }
            return _teacherNetworkComponent!!
        }

    fun init() {
        _teacherNetworkComponent = DaggerTeacherNetworkComponent.builder()
            .commonComponent(CommonComponentHolder.commonComponent)
            .build()
    }

    fun getComponentAndRegisterRefresh(refreshableComponentHolder: RefreshableComponentHolder): TeacherNetworkComponent {
        componentsToRefresh.add(refreshableComponentHolder)
        return teacherNetworkComponent
    }

    override fun refresh() {
        componentsToRefresh.forEach { it.refresh() }
        _teacherNetworkComponent = null
    }
}