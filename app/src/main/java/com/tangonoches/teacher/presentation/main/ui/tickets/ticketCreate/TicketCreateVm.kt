package com.tangonoches.teacher.presentation.main.ui.tickets.ticketCreate

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.*
import com.tangonoches.teacher.domain.interactors.tickets.ITicketsInteractor
import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import com.tangonoches.teacher.presentation.base.BaseVm
import java.util.*
import javax.inject.Inject

class TicketCreateVm @Inject constructor(
    private val ticketsInteractor: ITicketsInteractor
) : BaseVm() {

    val defaultStudentId = BehaviorRelay.create<Long>()
    val defaultTeacherId = BehaviorRelay.create<Long>()
    val defaultEventTypeId = BehaviorRelay.create<Long>()
    val defaultCountTypeId = BehaviorRelay.create<Long>()

    val ticketState = BehaviorRelay.createDefault<TicketFullClearModel>(TicketFullClearModel())

    val studentsState = BehaviorRelay.create<List<StudentShortModel>>()
    val teachersState = BehaviorRelay.create<List<TeacherShortModel>>()
    val eventTypesState = BehaviorRelay.create<List<TicketEventTypeModel>>()
    val countTypesState = BehaviorRelay.create<List<TicketCountTypeModel>>()

    val selectedStudent = BehaviorRelay.create<StudentShortModel>()
    val selectedTeacher = BehaviorRelay.create<TeacherShortModel>()
    val selectedEventType = BehaviorRelay.create<TicketEventTypeModel>()
    val selectedCountType = BehaviorRelay.create<TicketCountTypeModel>()

    val selectedStartDate = BehaviorRelay.create<Date>()
    val selectedEndDate = BehaviorRelay.create<Date>()
    val extraLessons = BehaviorRelay.createDefault(0)
    val increaseExtraLessonsActions = PublishRelay.create<Unit>()
    val decreaseExtraLessonsActions = PublishRelay.create<Unit>()
    val isInPair = BehaviorRelay.createDefault<Boolean>(false)
    val isNullify = BehaviorRelay.createDefault<Boolean>(false)

    val studentScanedAction = PublishRelay.create<Long>()
    val saveAction = PublishRelay.create<Unit>()

    init {
        val currentDate = Calendar.getInstance()
        selectedStartDate.accept(currentDate.time)
        currentDate.add(Calendar.MONTH, 1)
        selectedEndDate.accept(currentDate.time)
    }

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            ticketsInteractor.getAllStudents().subscribe { students ->
                studentsState.accept(students)
                if (defaultStudentId.hasValue()) {
                    val defaultId = defaultStudentId.getValueOrThrowNPE()
                    selectedStudent.accept(students.first { it.id == defaultId })
                } else {
                    selectedStudent.accept(students[0])
                }
            },
            ticketsInteractor.getAllTeachers().subscribe { teachers ->
                teachersState.accept(teachers)
                if (defaultTeacherId.hasValue()) {
                    val defaultId = defaultTeacherId.getValueOrThrowNPE()
                    selectedTeacher.accept(teachers.first { it.id == defaultId })
                } else {
                    selectedTeacher.accept(teachers[0])
                }
            },
            ticketsInteractor.getAllEventTypes().subscribe { eventTypes ->
                eventTypesState.accept(eventTypes)
                if (defaultEventTypeId.hasValue()) {
                    val defaultId = defaultEventTypeId.getValueOrThrowNPE()
                    selectedEventType.accept(eventTypes.first { it.id == defaultId })
                } else {
                    selectedEventType.accept(eventTypes[0])
                }
            },
            ticketsInteractor.getAllCountTypes().subscribe { countTypes ->
                countTypesState.accept(countTypes)
                if (defaultCountTypeId.hasValue()) {
                    val defaultId = defaultCountTypeId.getValueOrThrowNPE()
                    selectedCountType.accept(countTypes.first { it.id == defaultId })
                } else {
                    selectedCountType.accept(countTypes[0])
                }
            },
            selectedStudent.subscribe { item ->
                studentsState.accept(studentsState.getValueOrThrowNPE().map { it.setIsSelected(it.id == item.id) })
            },
            selectedTeacher.subscribe { item ->
                teachersState.accept(teachersState.getValueOrThrowNPE().map { it.setIsSelected(it.id == item.id) })
            },
            selectedEventType.subscribe { item ->
                eventTypesState.accept(eventTypesState.getValueOrThrowNPE().map {
                    it.setIsSelected(
                        it.id == item.id
                    )
                })
            },
            selectedCountType.subscribe { item ->
                countTypesState.accept(countTypesState.getValueOrThrowNPE().map {
                    it.setIsSelected(
                        it.id == item.id
                    )
                })
            },
            increaseExtraLessonsActions.subscribe {
                var currentLessons = extraLessons.getValueOrThrowNPE()
                currentLessons++
                extraLessons.accept(currentLessons)
            },
            decreaseExtraLessonsActions.subscribe {
                var currentLessons = extraLessons.getValueOrThrowNPE()
                currentLessons--
                extraLessons.accept(currentLessons)
            },
            saveAction.subscribe {
                createTicket()
            },
            studentScanedAction.subscribe { scannedId ->
                studentsState.getValueOrThrowNPE().firstOrNull { it.barcodeId == scannedId }
                    ?.let { foundStudent ->
                        selectedStudent.accept(foundStudent)
                    }
            }
        )
    }

    private fun createTicket() {
        ticketsInteractor.createTicket(
            TicketFullClearModel(
                id = DEFAULT_ID,
                isInPair = isInPair.getValueOrThrowNPE(),
                isNullify = isNullify.getValueOrThrowNPE(),
                teacherId = selectedTeacher.getValueOrThrowNPE().id,
                ticketBought_date = selectedStartDate.getValueOrThrowNPE(),
                ticketEndDate = selectedEndDate.getValueOrThrowNPE(),
                ticketStartDate = selectedStartDate.getValueOrThrowNPE(),
                ticketEventTypeId = selectedEventType.getValueOrThrowNPE().id,
                studentId = selectedStudent.getValueOrThrowNPE().id,
                extraLessons = extraLessons.getValueOrThrowNPE(),
                ticketCountTypeId = selectedCountType.getValueOrThrowNPE().id
            )
        )
            .subLoading()
            .subWithDefaultError {
                close()
            }
    }
}