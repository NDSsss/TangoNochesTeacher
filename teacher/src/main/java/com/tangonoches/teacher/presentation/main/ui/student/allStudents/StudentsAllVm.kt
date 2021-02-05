package com.tangonoches.teacher.presentation.main.ui.student.allStudents

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.domain.interactors.students.IStudentsInteractor
import ru.nds.core.presentation.base.BaseVm
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StudentsAllVm @Inject constructor(
    private val studentsInteractor: IStudentsInteractor
) : BaseVm() {
    val studentsState = BehaviorRelay.create<List<StudentShortModel>>()
    val studentsFiltredState = BehaviorRelay.create<List<StudentShortModel>>()

    val studentDetailAction = PublishRelay.create<Long>()
    val requestStudentsAction = PublishRelay.create<Unit>()
    val refreshStudentsAction = PublishRelay.create<Unit>()
    val searchQueryState = BehaviorRelay.createDefault<String>("")

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            requestStudentsAction.subscribe {
                binds.addAll(
                    studentsInteractor.getAllStudentsObservable().subscribe { students ->
                        Log.d("APP_TAG", "StudentsAllVm students $students")
                        studentsState.accept(students)
                    }
                )
                refreshStudentsAction.accept(Unit)
            },
            refreshStudentsAction.subscribe {
                studentsInteractor.refreshStudents().subLoading().subscribe()
            },
            studentsState.subscribe { students ->
                val searchQuery = (searchQueryState.value ?: "").toLowerCase()
                studentsFiltredState.accept(students.filter { currentStudent ->
                    currentStudent.name.toLowerCase().contains(searchQuery) ||
                            currentStudent.barcodeId.toString().contains(searchQuery)
                })
            },
            searchQueryState.skip(1).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { query ->
                    val students = studentsState.value ?: listOf()
                    val searchQuery = query.toLowerCase()
                    studentsFiltredState.accept(students.filter { currentStudent ->
                        currentStudent.name.toLowerCase().contains(searchQuery) ||
                                currentStudent.barcodeId.toString().contains(searchQuery)
                    })
                }


        )
    }
}