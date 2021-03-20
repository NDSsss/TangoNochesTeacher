package com.tangonoches.teacher.presentation.view.chips

import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import javax.inject.Inject

class TeacherChipsVm(
    private val lessonEditor: ILessonEditor
) : BaseChipsWidgetVm() {

    override fun onFirstStart() {
        super.onFirstStart()
        binds.addAll(
            lessonEditor.getCurrentLessonTeachersObservable().subscribe { list ->
                chipsState.accept(list.filter { it.isSelected })
                itemsState.accept(list)
            }
        )
    }

    override fun addItem(dto: ItemSelectedDto) {
        lessonEditor.addTeacher(dto.id)
    }

    override fun removeItem(dto: ItemSelectedDto) {
        lessonEditor.removeTeacher(dto.id)
    }
}