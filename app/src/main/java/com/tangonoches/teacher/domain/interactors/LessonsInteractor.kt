package com.tangonoches.teacher.domain.interactors

import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class LessonsInteractor(
    private val lessonsRepository: ILessonsRepository,
    private val groupsRepository: IGroupsRepository
) : ILessonsInteractor {
    override fun getFirstLessonsPageWithGroups(): Single<Pair<List<GroupFullModel>, List<LessonShortModel>>> =
//    lessonsRepository.getAllLessons(0).map { Pair(listOf<GroupFullModel>(),it) }
        Single.zip(
            groupsRepository.getAllGroups(),
            lessonsRepository.getAllLessons(1),
            BiFunction { t1, t2 -> Pair(t1, t2) }
        )

    override fun getLessonsPage(page: Int): Single<List<LessonShortModel>> =
        lessonsRepository.getAllLessons(page)
}