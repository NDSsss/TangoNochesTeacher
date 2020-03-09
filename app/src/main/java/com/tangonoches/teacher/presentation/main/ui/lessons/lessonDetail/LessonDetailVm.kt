package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import com.tangonoches.teacher.presentation.base.AdapterDto
import com.tangonoches.teacher.presentation.base.BaseVm
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType.EDIT
import io.reactivex.Single
import javax.inject.Inject

class LessonDetailVm @Inject constructor(
    private val lessonsInteractor: ILessonsInteractor
) : BaseVm() {
    val argLessonId = BehaviorRelay.create<Long>()
    val argViewType = BehaviorRelay.create<LessonDetailViewType>()

    val lessonRelay = BehaviorRelay.create<LessonFullModel>()
    val lessonNameRelay = BehaviorRelay.create<String>()

    val grpupsForAdapter = BehaviorRelay.create<AdapterDto<GroupFullModel>>()
    val groupsRelay = BehaviorRelay.createDefault<List<GroupFullModel>>(listOf())
    val groupSelectedAction = PublishRelay.create<Pair<Long, Int>>()

    val teachersChipRelay = BehaviorRelay.create<List<TeacherChipModel>>()
    val studentsRelay = BehaviorRelay.create<List<StudentShortModel>>()

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            viewCreatedAction.subscribe {
                if (argViewType.hasValue()) {
                    when (argViewType.value) {
                        EDIT -> lessonsInteractor.getLessonById(
                            argLessonId.value
                                ?: throw IllegalArgumentException("lesson id must be initialized")
                        )
                        else -> Single.just(LessonFullModel())
                    }
                } else {
                    Single.just(LessonFullModel())
                }
                    .flatMap { lesson ->
                        lessonNameRelay.accept(lesson.name)
                        lessonRelay.accept(lesson)
                        lessonsInteractor.getGroups()
                    }

                    .flatMap { groups ->
                        groupsRelay.accept(groups)
                        lessonsInteractor.getTeachers()
                    }
                    .flatMap { teachers ->
                        teachersChipRelay.accept(teachers.map { teacher ->
                            teacher.toChipModel(
                                lessonRelay.value?.teachers?.contains(teacher.id) ?: false
                            )
                        })
                        lessonsInteractor.getStudents()
                    }
                    .subscribe { students ->
                        studentsRelay.accept(students)
                    }
            },
            groupsRelay.subscribe { groups ->
                grpupsForAdapter.accept(AdapterDto(groups, lessonRelay.value?.groupId ?: -1L))
            },
            groupSelectedAction.subscribe { pair ->
                grpupsForAdapter.accept(
                    grpupsForAdapter.value!!.newItemSelected(
                        pair.first,
                        pair.second
                    )
                )
            }
        )
    }
}