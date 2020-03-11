package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import com.tangonoches.teacher.domain.editors.lesson.LessonEditor
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EditorsModule {
    @Provides
    @Singleton
    fun providesLessonEditor(
        teachersRepository: ITeachersRepository,
        studentsRepository: IStudentsRepository,
        groupsRepository: IGroupsRepository
    ): ILessonEditor =
        LessonEditor(teachersRepository, studentsRepository, groupsRepository)
}