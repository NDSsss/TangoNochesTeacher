package com.tangonoches.teacher.presentation.main.ui.student.studentDetail

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.domain.interactors.students.IStudentsInteractor
import com.tangonoches.teacher.presentation.base.BaseVm
import com.tangonoches.teacher.presentation.base.ShowDialogModel
import com.tangonoches.teacher.presentation.main.ui.student.allStudents.StudentsAllFragment
import com.tangonoches.teacher.presentation.main.ui.student.allStudents.StudentsAllFragment.StudentDetailViewType.EDIT
import io.reactivex.Single
import javax.inject.Inject

class StudentDetailVm @Inject constructor(
    private val studentsInteractor: IStudentsInteractor
) : BaseVm() {
    val viewTypeState = BehaviorRelay.create<StudentsAllFragment.StudentDetailViewType>()
    val studentId = BehaviorRelay.create<Long?>()

    val studentState = BehaviorRelay.create<StudentFullModel>()
    val studentEditState = BehaviorRelay.create<StudentFullModel>()

    val nameState = BehaviorRelay.create<String>()
    val phoneState = BehaviorRelay.create<String>()
    val barcodeIdState = BehaviorRelay.create<Long>()
    val extraInfoState = BehaviorRelay.create<String>()
    val vkState = BehaviorRelay.create<String>()
    val facebookState = BehaviorRelay.create<String>()
    val instagramState = BehaviorRelay.create<String>()
    val pointsState = BehaviorRelay.create<Int>()

    val saveStudentAction = PublishRelay.create<Unit>()
    val deleteAction = PublishRelay.create<Unit>()
    val deleteConfirmAction = PublishRelay.create<Unit>()

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            viewTypeState
                .subscribe { type ->
                    when (type) {
                        EDIT -> {
                            val studId = studentId.value
                                ?: throw NullPointerException("Student id can not be null")
                            studentsInteractor.getStudentById(studId)
                        }
                        else -> Single.just(StudentFullModel())
                    }
                        .subLoading()
                        .subWithDefaultError { studModel ->
                            studentState.accept(studModel)
                        }
                },
            studentState.subscribe { student ->
                studentEditState.accept(student)
            },
            studentEditState.subscribe { student ->
                nameState.accept(student.name)
                phoneState.accept(student.phone)
                barcodeIdState.accept(student.barcodeId)
                extraInfoState.accept(student.extraInfo)
                vkState.accept(student.vkProfilelink)
                facebookState.accept(student.facebookProfileLink)
                instagramState.accept(student.instagramProfileLink)
                pointsState.accept(student.points)
            },
            saveStudentAction.subscribe {
                saveStudent()
            },
            deleteAction.subscribe {
                showDialog(
                    ShowDialogModel(
                        message = R.string.student_detail_delete_dialog_message,
                        negativeButtonRes = R.string.student_detail_delete_dialog_positive,
                        negativeAction = { deleteConfirmAction.accept(Unit) },
                        positiveButtonRes = R.string.student_detail_delete_dialog_negative
                    )
                )
            },
            deleteConfirmAction.subscribe {
                studentsInteractor.deleteStudent(studentState.getValueOrThrowNPE())
                    .subLoading()
                    .subWithDefaultError {
                        close()
                    }
            }
        )
    }

    private fun saveStudent() {
        val currStudent =
            studentEditState.value ?: throw NullPointerException("Student can not be null")
        val studentToSave = currStudent.copy(
            barcodeId = barcodeIdState.getValueOrThrowNPE(),
            name = nameState.getValueOrThrowNPE(),
            phone = phoneState.getValueOrThrowNPE(),
            extraInfo = extraInfoState.getValueOrThrowNPE(),
            vkProfilelink = vkState.getValueOrThrowNPE(),
            facebookProfileLink = facebookState.getValueOrThrowNPE(),
            instagramProfileLink = instagramState.getValueOrThrowNPE(),
            points = pointsState.getValueOrThrowNPE()
        )
        binds.addAll(
            studentsInteractor.saveStudent(studentToSave)
                .subLoading()
                .subWithDefaultError {
                    close()
                }
        )

    }
}