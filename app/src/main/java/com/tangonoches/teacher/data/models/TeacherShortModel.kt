package com.tangonoches.teacher.data.models

import com.allyants.chipview.BaseChipItem


data class TeacherShortModel(
    private val id: Long = 0,
    private var isSelected: Boolean = false,
    val name: String = ""
) : BaseChipItem {
    override fun isSelected(): Boolean = isSelected

    override fun setIsSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }

    override fun getId(): Long = id
    override fun toString(): String = name
}


data class TeacherChipModel(val teacher: TeacherShortModel, var isSelected: Boolean) {
    override fun toString(): String {
        return teacher.name
    }
}

fun TeacherShortModel.toChipModel(isSelected: Boolean) =
    TeacherChipModel(this, isSelected)