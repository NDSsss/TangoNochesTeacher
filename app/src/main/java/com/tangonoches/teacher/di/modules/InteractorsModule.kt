package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import com.tangonoches.teacher.domain.interactors.LessonsInteractor
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {
    @Provides
    @Singleton
    fun provideLessonsInteractor(
        lessonsRepository: ILessonsRepository,
        groupsRepository: IGroupsRepository
    ): ILessonsInteractor =
        LessonsInteractor(lessonsRepository, groupsRepository)
}