package com.tangonoches.teacher.presentation.main.ui.lessons.allLessons

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import com.tangonoches.teacher.presentation.base.BaseVm
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

    val requestLessonsAction = PublishRelay.create<Unit>()
    val requestNextPageAction = PublishRelay.create<Unit>()

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            requestLessonsAction.subscribe {
                currentPage = 1
                lessonsInteractor.getFirstLessonsPageWithGroups()
                    .subscribe { pair ->
                        groupsValue.accept(pair.first)
                        currentPage++
                        if(pair.second.isNotEmpty()) {
                            lessonsLeftEffect.accept(true)
                            allLessonsValue.accept(allLessonsValue.value!!.plus(pair.second))
                        } else {
                            lessonsLeftEffect.accept(false)
                        }
                    }
            },
            requestNextPageAction.subscribe {
                lessonsInteractor.getLessonsPage(currentPage)
                    .subscribe { newLessons ->
                        currentPage++
                        if(newLessons.isNotEmpty()) {
                            lessonsLeftEffect.accept(true)
                            allLessonsValue.accept(allLessonsValue.value!!.plus(newLessons))
                        } else {
                            lessonsLeftEffect.accept(false)
                        }
                    }
            }
        )
    }
}