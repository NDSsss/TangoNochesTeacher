package com.tangonoches.teacher.presentation.view.chips

import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import javax.inject.Inject

class StudentsChipsVm @Inject constructor(
    private val lessonEditor: ILessonEditor
) : BaseChipsWidgetVm() {

    override fun onFirstStart() {
        super.onFirstStart()
        binds.addAll(
            lessonEditor.getCurrentLessonStudentsObservable().subscribe { list ->
                chipsState.accept(list.filter { it.isSelected })
                itemsState.accept(list)
            }
        )
    }

    override fun addItem(dto: ItemSelectedDto) {
        lessonEditor.addStudent(dto.id)
    }

    override fun removeItem(dto: ItemSelectedDto) {
        lessonEditor.removeStudent(dto.id)
    }
}