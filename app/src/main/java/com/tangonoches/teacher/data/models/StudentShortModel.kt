package com.tangonoches.teacher.data.models

import com.allyants.chipview.BaseChipItem

data class StudentShortModel(
    val barcodeId: Long = 0,
    private val id: Long = 0,
    private var isSelected: Boolean = false,
    val name: String = "",
    val phone: String = ""
) : BaseChipItem {
    override fun isSelected(): Boolean = isSelected
    override fun setIsSelected(isSelected: Boolean){
        this.isSelected = isSelected
    }

    override fun getId(): Long = id
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