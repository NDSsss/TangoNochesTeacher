package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.data.models.TeacherShortModel
import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import com.tangonoches.teacher.presentation.base.AdapterDto
import com.tangonoches.teacher.presentation.base.BaseVm
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType.EDIT
import com.tangonoches.teacher.presentation.view.chips.StudentsChipsVm
import com.tangonoches.teacher.presentation.view.chips.TeacherChipsVm
import io.reactivex.Single
import javax.inject.Inject

class LessonDetailVm @Inject constructor(
    private val lessonsInteractor: ILessonsInteractor,
    private val lessonEitor: ILessonEditor
) : BaseVm() {

//    lateinit var teachersChipsWidgetVm: TeacherChipsVm
//    fun settTeachersChipsWidgetVm(teachersChipsWidgetVm: TeacherChipsVm) {
//        this.teachersChipsWidgetVm = teachersChipsWidgetVm
//        this.teachersChipsWidgetVm.itemsObservable = studentsChipRelay.map { it }
//    }
//
//    lateinit var studentsChipsWidgetVm: StudentsChipsVm
//    fun settStudentsChipsWidgetVm(studentsChipsWidgetVm: StudentsChipsVm) {
//        this.studentsChipsWidgetVm = studentsChipsWidgetVm
//        this.studentsChipsWidgetVm.itemsObservable = studentsChipRelay.map { it }
//    }

    val argLessonId = BehaviorRelay.create<Long>()
    val argViewType = BehaviorRelay.create<LessonDetailViewType>()

    val lessonRelay = BehaviorRelay.create<LessonFullModel>()
    val lessonNameRelay = BehaviorRelay.create<String>()

    val grpupsForAdapter = BehaviorRelay.create<AdapterDto<GroupFullModel>>()
    val groupsRelay = BehaviorRelay.createDefault<List<GroupFullModel>>(listOf())
    val groupSelectedAction = PublishRelay.create<Pair<Long, Int>>()

//    val teachersChipRelay = BehaviorRelay.create<List<TeacherShortModel>>()
//    val studentsChipRelay = BehaviorRelay.create<List<StudentShortModel>>()

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
                        lessonEitor.setLessonToEdit(lesson)
                        lessonNameRelay.accept(lesson.name)
                        lessonRelay.accept(lesson)
                        lessonsInteractor.getGroups()
                    }.subscribe { groups ->
                        groupsRelay.accept(groups)
                    }

//                    .flatMap { groups ->
//                        groupsRelay.accept(groups)
//                        lessonsInteractor.getTeachers()
//                    }
//                    .flatMap { teachers ->
//                        teachersChipRelay.accept(teachers.map { teacher ->
//                            teacher.setIsSelected(
//                                lessonRelay.value?.teachers?.contains(teacher.id) ?: false
//                            )
//                            teacher
//                        })
//                        lessonsInteractor.getStudents()
//                    }
//                    .subscribe { students ->
//                        studentsChipRelay.accept(students.map { student ->
//                            student.setIsSelected(
//                                lessonRelay.value?.students?.contains(student.id) ?: false
//                            )
//                            student
//                        })
//                    }
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