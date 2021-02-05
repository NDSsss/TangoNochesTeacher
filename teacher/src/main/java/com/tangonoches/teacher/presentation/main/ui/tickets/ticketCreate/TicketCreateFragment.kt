package com.tangonoches.teacher.presentation.main.ui.tickets.ticketCreate

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.*
import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import com.tangonoches.teacher.presentation.base.BaseTeacherFragment
import com.tangonoches.teacher.presentation.base.GroupsSpinnerAdapter
import com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail.closeSpinner
import com.tangonoches.teacher.presentation.main.ui.tickets.ticketCreate.TicketCreateFragment.DatePickerType.END
import com.tangonoches.teacher.presentation.main.ui.tickets.ticketCreate.TicketCreateFragment.DatePickerType.START
import com.tangonoches.teacher.presentation.view.dialogScanStudent.ScanStudentDialog
import kotlinx.android.synthetic.main.frag_ticket_create.*
import java.text.SimpleDateFormat
import java.util.*

const val DEFAULT_STUDENT_ID = "DEFAULT_STUDENT_ID"

class TicketCreateFragment : BaseTeacherFragment<TicketCreateVm>() {
    override val layoutId: Int = R.layout.frag_ticket_create
    override fun getVmClass(): Class<TicketCreateVm> = TicketCreateVm::class.java

    override fun preCreateVmBinds() {
        arguments?.let { args ->
            val id = args.getLong(DEFAULT_STUDENT_ID, DEFAULT_ID)
            if (id != DEFAULT_ID) {
                vm.defaultStudentId.accept(id)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_ticket_create_student_sp.adapter = GroupsSpinnerAdapter { item ->
            vm.selectedStudent.accept(item as StudentShortModel)
        }
        frag_ticket_create_teacher_sp.adapter = GroupsSpinnerAdapter { item ->
            vm.selectedTeacher.accept(item as TeacherShortModel)
        }
        frag_ticket_create_event_type_sp.adapter = GroupsSpinnerAdapter { item ->
            vm.selectedEventType.accept(item as TicketEventTypeModel)
        }
        frag_ticket_create_count_type_sp.adapter = GroupsSpinnerAdapter { item ->
            vm.selectedCountType.accept(item as TicketCountTypeModel)
        }
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            vm.studentsState.subscribe { items ->
                Log.d("APP_TAG", "TicketCreateFragment vm.studentsState $items")
                (frag_ticket_create_student_sp.adapter as? GroupsSpinnerAdapter)?.let { adapter ->
                    Log.d("APP_TAG", "TicketCreateFragment vm.studentsState has adapter $items")
                    adapter.groupsList = items
                    val selectedIndex = items.getSelectedPosition()
                    if (selectedIndex > -1) {
                        frag_ticket_create_student_sp.setSelection(selectedIndex)
                    }
                    closeSpinner(frag_ticket_create_student_sp)
                }
            },
            vm.teachersState.subscribe { items ->
                (frag_ticket_create_teacher_sp.adapter as? GroupsSpinnerAdapter)?.let { adapter ->
                    adapter.groupsList = items
                    val selectedIndex = items.getSelectedPosition()
                    if (selectedIndex > -1) {
                        frag_ticket_create_teacher_sp.setSelection(selectedIndex)
                    }
                    closeSpinner(frag_ticket_create_teacher_sp)
                }
            },
            vm.eventTypesState.subscribe { items ->
                (frag_ticket_create_event_type_sp.adapter as? GroupsSpinnerAdapter)?.let { adapter ->
                    adapter.groupsList = items
                    val selectedIndex = items.getSelectedPosition()
                    if (selectedIndex > -1) {
                        frag_ticket_create_event_type_sp.setSelection(selectedIndex)
                    }
                    closeSpinner(frag_ticket_create_event_type_sp)
                }
            },
            vm.countTypesState.subscribe { items ->
                (frag_ticket_create_count_type_sp.adapter as? GroupsSpinnerAdapter)?.let { adapter ->
                    adapter.groupsList = items
                    val selectedIndex = items.getSelectedPosition()
                    if (selectedIndex > -1) {
                        frag_ticket_create_count_type_sp.setSelection(selectedIndex)
                    }
                    closeSpinner(frag_ticket_create_count_type_sp)
                }
            },
            vm.selectedStartDate.subscribe { date ->
                frag_ticket_create_start_date_et.setText(date.toTicketCreateDate())
            },
            vm.selectedEndDate.subscribe { date ->
                frag_ticket_create_end_date_et.setText(date.toTicketCreateDate())
            },
            vm.extraLessons.subscribe { num ->
                frag_ticket_create_extra_lessons_et.setText(num.toString())
            },
            frag_ticket_create_extra_lessons_plus_btn.clicks().subscribe {
                vm.increaseExtraLessonsActions.accept(Unit)
            },
            frag_ticket_create_extra_lessons_minus_btn.clicks().subscribe {
                vm.decreaseExtraLessonsActions.accept(Unit)
            },
            frag_ticket_create_is_in_pair_cb.clicks().subscribe {
                vm.isInPair.accept(frag_ticket_create_is_in_pair_cb.isChecked)
            },
            frag_ticket_create_is_nullify_cb.clicks().subscribe {
                vm.isNullify.accept(frag_ticket_create_is_nullify_cb.isChecked)
            },
            vm.isInPair.subscribe {
                frag_ticket_create_is_in_pair_cb.isChecked = it
            },
            vm.isNullify.subscribe {
                frag_ticket_create_is_nullify_cb.isChecked = it
            },
            frag_ticket_create_save_btn.clicks().subscribe {
                vm.saveAction.accept(Unit)
            },
            frag_ticket_create_student_scan.clicks().subscribe {
                openScanDialog()
            },
            frag_ticket_create_start_date_picker.clicks().subscribe {
                openDatePicker(START)
            },
            frag_ticket_create_end_date_picker.clicks().subscribe {
                openDatePicker(END)
            }
        )
    }

    private fun openScanDialog() {
        ScanStudentDialog {
            vm.studentScanedAction.accept(it)
        }
            .show(childFragmentManager, ScanStudentDialog.TAG)
    }

    private fun openDatePicker(type: DatePickerType) {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            R.style.AppDialog,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                when (type) {
                    START -> {
                        vm.selectedStartDate.accept(calendar.time)
                    }
                    END -> {
                        vm.selectedEndDate.accept(calendar.time)
                    }
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    enum class DatePickerType {
        START,
        END
    }
}

fun Date.toTicketCreateDate(): String =
    SimpleDateFormat("yyyy.MM.dd").format(this)