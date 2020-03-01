package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import com.tangonoches.teacher.presentation.base.BaseVm
import javax.inject.Inject

class LessonDetailVm @Inject constructor(
    private val lessonsRepository: ILessonsRepository
) : BaseVm() {
}