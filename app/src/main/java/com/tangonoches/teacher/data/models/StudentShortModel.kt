package com.tangonoches.teacher.data.models

import com.tangonoches.teacher.presentation.view.chips.BaseChipItem

data class StudentShortModel(
    val barcodeId: Long = 0,
    override val id: Long = 0,
    override val isSelected: Boolean = false,
    val name: String = "",
    val phone: String = ""
) : BaseChipItem {

    fun setIsSelected(isSelected: Boolean): StudentShortModel =
        this.copy(isSelected = isSelected)

    override fun toString(): String = name

}

data class StudentShortChipModel(
    val student: StudentShortModel,
    val isSelected: Boolean
)

fun StudentShortModel.toChip(isSelected: Boolean) =
    StudentShortChipModel(
        this,
        isSelected
    )