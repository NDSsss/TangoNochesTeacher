package com.tangonoches.teacher.data.models

import com.tangonoches.teacher.presentation.view.chips.BaseChipItem


data class TeacherShortModel(
    override val id: Long = 0,
    override val isSelected: Boolean = false,
    val name: String = ""
) : BaseChipItem {

    fun setIsSelected(isSelected: Boolean): TeacherShortModel =
        this.copy(isSelected = isSelected)

    override fun toString(): String = name
}


data class TeacherChipModel(val teacher: TeacherShortModel, var isSelected: Boolean) {
    override fun toString(): String {
        return teacher.name
    }
}

fun TeacherShortModel.toChipModel(isSelected: Boolean) =
    TeacherChipModel(this, isSelected)