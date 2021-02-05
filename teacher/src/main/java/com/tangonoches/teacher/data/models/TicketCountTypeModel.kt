package com.tangonoches.teacher.data.models

import com.tangonoches.teacher.presentation.view.chips.BaseChipItem

data class TicketCountTypeModel(
    override val id: Long = 0,
    override val isSelected: Boolean = false,
    val lessonsCount: Int = 0,
    val name: String = "",
    val price: Double = 0.0
) : BaseChipItem {
    fun setIsSelected(isSelected: Boolean): TicketCountTypeModel =
        this.copy(isSelected = isSelected)

    override fun toString(): String = name
}