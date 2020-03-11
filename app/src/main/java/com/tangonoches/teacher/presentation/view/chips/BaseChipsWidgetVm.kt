package com.tangonoches.teacher.presentation.view.chips

import com.allyants.chipview.BaseChipItem
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.presentation.view.base.BaseWidgetVm

const val ITEMS_DEFAULT_VISIBILITY = false

open class BaseChipsWidgetVm : BaseWidgetVm() {
    val itemsVisibilityToggleAction = PublishRelay.create<Unit>()
    val itemsVisibleState = BehaviorRelay.createDefault(ITEMS_DEFAULT_VISIBILITY)

    val chipsState = BehaviorRelay.create<List<BaseChipItem>>()
    val itemsState = BehaviorRelay.create<List<BaseChipItem>>()


    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            itemsVisibilityToggleAction.subscribe {
                itemsVisibleState.accept(itemsVisibleState.value?.not() ?: ITEMS_DEFAULT_VISIBILITY)
            }
        )
    }
}