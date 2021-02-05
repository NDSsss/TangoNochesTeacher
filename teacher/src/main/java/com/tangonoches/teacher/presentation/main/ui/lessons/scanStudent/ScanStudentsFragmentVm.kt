package com.tangonoches.teacher.presentation.main.ui.lessons.scanStudent

import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.domain.editors.lesson.ILessonEditor
import ru.nds.core.presentation.base.BaseVm
import javax.inject.Inject

class ScanStudentsFragmentVm @Inject constructor(
    private val lessonEditor: ILessonEditor
) : BaseVm() {
    var permissionsGranted = false

    val codeScannedAction = PublishRelay.create<Long>()
    val confirmAction = PublishRelay.create<Unit>()

    lateinit var students: List<StudentShortModel>

    init {
        binds.add(
            lessonEditor.getAllStudentsObservable().subscribe { list ->
                students = list
            }
        )
    }

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            codeScannedAction.subscribe { scannedCode ->
                val scannedStudent = students.firstOrNull { it.barcodeId == scannedCode }
                if (scannedStudent != null) {
                    showToast(scannedStudent.name)
                    lessonEditor.addStudent(scannedStudent.id)
                } else {
                    showToast("Ученик с такой картой не зарегестрирован")
                }
            },
            confirmAction.subscribe {
                close()
            }
        )
    }
}