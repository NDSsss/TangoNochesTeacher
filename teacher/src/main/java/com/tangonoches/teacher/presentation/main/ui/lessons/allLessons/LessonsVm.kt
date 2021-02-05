package com.tangonoches.teacher.presentation.main.ui.lessons.allLessons

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import ru.nds.core.presentation.base.BaseVm
import javax.inject.Inject

class LessonsVm @Inject constructor(
    private val lessonsInteractor: ILessonsInteractor
) : BaseVm() {

    init {
        Log.d("APP_TAG", "LessonsVm init")
    }

    val groupsValue = BehaviorRelay.create<List<GroupFullModel>>()
    val allLessonsValue = BehaviorRelay.createDefault<List<LessonShortModel>>(listOf())
    val lessonsLeftEffect = PublishRelay.create<Boolean>()
    var currentPage = 1

    val lessonsRefreshAction = PublishRelay.create<Unit>()

    val requestLessonsAction = PublishRelay.create<Unit>()
    val requestNextPageAction = PublishRelay.create<Unit>()

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            requestLessonsAction
                .filter { groupsValue.hasValue().not() }
                .subWithDefaultError {
                    currentPage = 1
                    lessonsInteractor.getGroups().flatMap { groups ->
                        groupsValue.accept(groups)
                        lessonsInteractor.getLessonsPage(currentPage)
                    }
                        .subLoading()
                        .subscribe { lessons ->
                            currentPage++
                            if (lessons.isNotEmpty()) {
                                lessonsLeftEffect.accept(true)
                                allLessonsValue.accept(lessons)
                            } else {
                                lessonsLeftEffect.accept(false)
                            }
                        }
                },
            requestNextPageAction.subscribe {
                lessonsInteractor.getLessonsPage(currentPage)
                    .subLoading()
                    .subWithDefaultError { newLessons ->
                        currentPage++
                        if (newLessons.isNotEmpty()) {
                            lessonsLeftEffect.accept(true)
                            allLessonsValue.accept(allLessonsValue.value!!.plus(newLessons))
                        } else {
                            lessonsLeftEffect.accept(false)
                        }
                    }
            },
            lessonsInteractor.lessonsRefreshObservable().subWithDefaultError { refresherLessons ->
                currentPage = 2
                lessonsLeftEffect.accept(true)
                allLessonsValue.accept(refresherLessons)
            },
            lessonsRefreshAction.subscribe {
                binds.add(
                    lessonsInteractor.refreshLessons()
                        .subLoading()
                        .subWithDefaultError{

                        }
                )
            }
        )
    }
}