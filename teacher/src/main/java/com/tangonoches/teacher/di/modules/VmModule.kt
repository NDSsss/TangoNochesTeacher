package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.presentation.main.MainDrawerVm
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonsVm
import com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail.LessonDetailVm
import com.tangonoches.teacher.presentation.main.ui.lessons.scanStudent.ScanStudentsFragmentVm
import com.tangonoches.teacher.presentation.main.ui.student.allStudents.StudentsAllVm
import com.tangonoches.teacher.presentation.main.ui.student.studentDetail.StudentDetailVm
import com.tangonoches.teacher.presentation.main.ui.tickets.ticketCreate.TicketCreateVm
import com.tangonoches.teacher.presentation.main.ui.tickets.ticketsAll.TicketsAllVm
import com.tangonoches.teacher.presentation.view.chips.StudentsChipsVm
import com.tangonoches.teacher.presentation.view.chips.TeacherChipsVm
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val monolitVmModule = module {
    viewModel {
        LessonsVm(
            lessonsInteractor = get()
        )
    }
    viewModel {
        MainDrawerVm(
            prefsStorage = get()
        )
    }
    viewModel {
        LessonDetailVm(
            lessonsInteractor = get(),
            lessonEditor = get(),
        )
    }
    viewModel {
        TeacherChipsVm(
            lessonEditor = get()
        )
    }
    viewModel {
        StudentsChipsVm(
            lessonEditor = get()
        )
    }
    viewModel {
        ScanStudentsFragmentVm(
            lessonEditor = get()
        )
    }
    viewModel {
        StudentsAllVm(
            studentsInteractor = get()
        )
    }
    viewModel {
        StudentDetailVm(
            studentsInteractor = get()
        )
    }
    viewModel {
        TicketsAllVm(
            ticketsInteractor = get()
        )
    }
    viewModel {
        TicketCreateVm(
            ticketsInteractor = get()
        )
    }
}