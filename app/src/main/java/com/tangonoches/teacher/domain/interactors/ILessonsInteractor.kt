package com.tangonoches.teacher.domain.interactors

import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import io.reactivex.Single

interface ILessonsInteractor {
    fun getFirstLessonsPageWithGroups(): Single<Pair<List<GroupFullModel>, List<LessonShortModel>>>
    fun getLessonsPage(page: Int):Single<List<LessonShortModel>>
}