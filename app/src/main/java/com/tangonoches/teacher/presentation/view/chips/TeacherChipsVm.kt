package com.tangonoches.teacher.presentation.view.chips

import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import javax.inject.Inject

class TeacherChipsVm @Inject constructor(
    private val lessonEditor: ILessonEditor
) : BaseChipsWidgetVm() {
    init {
        binds.addAll(
            lessonEditor.getAllTeachersObservable().subscribe { list ->
                itemsState.accept(list)
            },
            lessonEditor.getCurrentLessonTeachersObservable().subscribe { list ->
                chipsState.accept(list)
            }
        )
    }
}