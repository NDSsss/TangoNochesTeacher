package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import com.tangonoches.teacher.presentation.base.BaseVm
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType.EDIT
import io.reactivex.Single
import javax.inject.Inject

class LessonDetailVm @Inject constructor(
    private val lessonsInteractor: ILessonsInteractor,
    private val lessonEditor: ILessonEditor
) : BaseVm() {

    val argLessonId = BehaviorRelay.create<Long>()
    val argViewType = BehaviorRelay.create<LessonDetailViewType>()

    val lessonRelay = BehaviorRelay.create<LessonFullModel>()
    val lessonNameRelay = BehaviorRelay.create<String>()

    val groupsRelay = BehaviorRelay.createDefault<List<GroupFullModel>>(listOf())
    val groupSelectedAction = PublishRelay.create<Long>()

    val nameChagedAction = PublishRelay.create<String>()

    val saveAction = PublishRelay.create<Unit>()

    override fun onFirstStart() {
        super.onFirstStart()
        binds.addAll(
            argViewType.subscribe {
                when (argViewType.value) {
                    EDIT -> lessonsInteractor.getLessonById(
                        argLessonId.value
                            ?: throw IllegalArgumentException("lesson id must be initialized")
                    )
                    else -> Single.just(LessonFullModel())
                }
                    .flatMap { lesson ->
                        lessonEditor.setLessonToEdit(lesson)
                        lessonNameRelay.accept(lesson.name)
                        lessonRelay.accept(lesson)
                        lessonsInteractor.getGroups()
                    }.subscribe { groups ->
                        groupsRelay.accept(groups)
                    }
            }
        )
    }

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            lessonEditor.getCurrentLessonGroupObservable().subscribe { groups ->
                groupsRelay.accept(groups)
            },
            groupSelectedAction.subscribe { id ->
                lessonEditor.groupSelected(id)
            },
            nameChagedAction.subscribe {
                lessonEditor.setName(it)
            },
            saveAction.subscribe {
                binds.add(
                    lessonEditor.saveLesson()
                        .subscribe(
                            {
                                closeRelay.accept(Unit)
                            },
                            {
                                errorRelay.accept(it.message ?: "error")
                            }
                        )
                )
            }
        )
    }

    override fun onCleared() {
        lessonEditor.clearAll()
        super.onCleared()
    }
}