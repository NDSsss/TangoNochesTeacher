package com.tangonoches.teacher.domain.repositories.teachers

import com.jakewharton.rxrelay2.BehaviorRelay
import com.tangonoches.teacher.data.models.TeacherShortModel
import com.tangonoches.teacher.domain.datasources.web.teachers.ITeachersDataSource
import io.reactivex.Single

class TeachersRepository(
    private val teachersDataSource: ITeachersDataSource
) : ITeachersRepository {

    private val teachers = BehaviorRelay.create<List<TeacherShortModel>>()

    override fun getAllTeachers(): Single<List<TeacherShortModel>> =
        if (teachers.hasValue().not()) {
            teachersDataSource.getAllTeachers()
                .doOnSuccess { list -> teachers.accept(list) }
        } else {
            Single.just(teachers.value)
        }
}