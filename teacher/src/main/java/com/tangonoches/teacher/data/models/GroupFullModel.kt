package com.tangonoches.teacher.data.models

import com.tangonoches.teacher.presentation.view.chips.BaseChipItem

data class GroupFullModel(
    val address: String = "",
    val createdAt: String? = null,
    val deletedAt: String? = null,
    val firstLessonTime: String = "",
    override val id: Long = 0,
    val name: String = "",
    val secondLessonTime: String = "",
    val ticketEventTypeId: Int = 0,
    val updatedAt: String? = null,
    override val isSelected: Boolean = false
) : BaseChipItem {
    fun setIsSelected(isSelected: Boolean): GroupFullModel =
        this.copy(isSelected = isSelected)

    override fun toString(): String = name
}

fun List<BaseChipItem>.getSelectedPosition(): Int =
    this.indexOfFirst { item -> item.isSelected }