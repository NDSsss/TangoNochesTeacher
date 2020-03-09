package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.allyants.chipview.ChipAdapter
import com.tangonoches.teacher.data.models.TeacherShortModel

class TeachersChipAdapter(
) : ChipAdapter() {


    var teachersChipList: List<TeacherChipModel> = listOf()
    set(value) {
        field = value
        refresh()
    }

    override fun createSearchView(p0: Context?, p1: Boolean, p2: Int): View {
        val ed = EditText(p0)
        return ed
    }

    override fun getItem(p0: Int): Any = teachersChipList[p0]

    override fun createChip(p0: Context?, p1: Int): View {
        val tv = TextView(p0)
        tv.setText(teachersChipList[p1].teacher.name)
        return tv
    }

    override fun isSelected(p0: Int): Boolean = teachersChipList[p0].isSelected

    override fun getCount(): Int = teachersChipList.size
}

data class TeacherChipModel(val teacher: TeacherShortModel, var isSelected: Boolean)

fun TeacherShortModel.toChipModel(isSelected: Boolean) =
    TeacherChipModel(this, isSelected)