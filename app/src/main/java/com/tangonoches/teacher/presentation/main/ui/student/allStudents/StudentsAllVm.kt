package com.tangonoches.teacher.presentation.main.ui.student.allStudents

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.domain.interactors.students.IStudentsInteractor
import com.tangonoches.teacher.presentation.base.BaseVm
import io.reactivex.Completable
import java.sql.Time
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StudentsAllVm @Inject constructor(
    private val studentsInteractor: IStudentsInteractor
) : BaseVm() {
    val studentsState = BehaviorRelay.create<List<StudentShortModel>>()

    val studentDetailAction = PublishRelay.create<Long>()
    val requestStudentsAction = PublishRelay.create<Unit>()
    val refreshStudentsAction = PublishRelay.create<Unit>()

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            requestStudentsAction.subscribe {
                binds.addAll(
                    studentsInteractor.getAllStudents().subscribe { students ->
                        Log.d("APP_TAG", "StudentsAllVm students $students")
                        studentsState.accept(students)
                    }
                )
            },
            refreshStudentsAction.subscribe {
                Completable.complete().delay(3,TimeUnit.SECONDS)
                    .subscribe { loadingState.accept(false) }
            }


        )
    }
}