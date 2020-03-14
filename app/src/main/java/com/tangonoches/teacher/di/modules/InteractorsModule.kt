package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import com.tangonoches.teacher.domain.interactors.LessonsInteractor
import com.tangonoches.teacher.domain.interactors.students.IStudentsInteractor
import com.tangonoches.teacher.domain.interactors.students.StudentsInteractor
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {
    @Provides
    @Singleton
    fun provideLessonsInteractor(
        lessonsRepository: ILessonsRepository,
        groupsRepository: IGroupsRepository,
        teachersRepository: ITeachersRepository,
        studentsRepository: IStudentsRepository
    ): ILessonsInteractor =
        LessonsInteractor(
            lessonsRepository,
            groupsRepository,
            teachersRepository,
            studentsRepository
        )

    @Provides
    @Singleton
    fun provideStudentsInteractor(
        studentsRepository: IStudentsRepository
    ): IStudentsInteractor =
        StudentsInteractor(
            studentsRepository
        )
}