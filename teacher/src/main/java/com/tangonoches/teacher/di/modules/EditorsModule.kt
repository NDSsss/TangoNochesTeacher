package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import com.tangonoches.teacher.domain.editors.lesson.LessonEditor
import org.koin.dsl.module

val monoliteEditorsModule = module {
    factory<ILessonEditor> {
        LessonEditor(
            teachersRepository = get(),
            studentsRepository = get(),
            groupsRepository = get(),
            lessonsRepository = get()
        )
    }
}