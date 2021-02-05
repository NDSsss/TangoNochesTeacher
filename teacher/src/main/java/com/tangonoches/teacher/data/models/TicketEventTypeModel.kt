package com.tangonoches.teacher.data.models

import com.tangonoches.teacher.presentation.view.chips.BaseChipItem

data class TicketEventTypeModel(
    override val id: Long = 0,
    override val isSelected: Boolean = false,
    val name: String = ""
) : BaseChipItem {
    fun setIsSelected(isSelected: Boolean): TicketEventTypeModel =
        this.copy(isSelected = isSelected)

    override fun toString(): String = name
}