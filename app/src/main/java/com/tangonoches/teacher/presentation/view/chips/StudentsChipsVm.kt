package com.tangonoches.teacher.presentation.view.chips

import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import javax.inject.Inject

class StudentsChipsVm @Inject constructor(
    private val lessonEditor: ILessonEditor
) : BaseChipsWidgetVm() {
    init {
        binds.addAll(
            lessonEditor.getCurrentLessonStudentsObservable().subscribe { list ->
                chipsState.accept(list)
            },
            lessonEditor.getAllStudentsObservable().subscribe { list ->
                itemsState.accept(list)
            }
        )
    }
}