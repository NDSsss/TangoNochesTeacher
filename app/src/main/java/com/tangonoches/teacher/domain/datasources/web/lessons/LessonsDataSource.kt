package com.tangonoches.teacher.domain.datasources.web.lessons

import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import com.tangonoches.teacher.data.responses.lessons.toCreateDto
import com.tangonoches.teacher.data.responses.lessons.toModel
import com.tangonoches.teacher.data.responses.lessons.toUpdateDto
import com.tangonoches.teacher.domain.datasources.web.login.subToThreads
import com.tangonoches.teacher.domain.repositories.constants.IConstantsRepository
import com.tangonoches.teacher.domain.services.LessonsService
import io.reactivex.Completable
import io.reactivex.Single

class LessonsDataSource(
    private val constantsRepository: IConstantsRepository,
    private val lessonsService: LessonsService
) : ILessonsDataSource {
    override fun getAllLessons(page: Int): Single<List<LessonShortModel>> =
        lessonsService.getAllLessons(
            page,
            constantsRepository.itemsOnPage
        ).map { response -> response.data.map { item -> item.toModel() } }
            .subToThreads()

    override fun getLessonById(id: Long): Single<LessonFullModel> =
        lessonsService.getLessonById(id)
            .subToThreads()
            .map { response ->
                response.data.toModel()
            }

    override fun updateLesson(lesson: LessonFullModel): Completable =
        lessonsService.updateLesson(lesson.toUpdateDto())
            .subToThreads()

    override fun createLesson(lesson: LessonFullModel): Completable =
        lessonsService.createLesson(lesson.toCreateDto())
            .subToThreads()

    override fun deleteLesson(lesson: LessonFullModel): Completable =
        lessonsService.deleteLesson(lesson.id)
            .subToThreads()
}