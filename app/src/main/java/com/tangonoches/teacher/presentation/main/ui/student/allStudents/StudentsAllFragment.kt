package com.tangonoches.teacher.presentation.main.ui.student.allStudents

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseVmFragment
import com.tangonoches.teacher.presentation.main.ui.student.studentDetail.STUDENT_ID
import com.tangonoches.teacher.presentation.main.ui.student.studentDetail.STUDENT_VIEW_TYPE
import kotlinx.android.synthetic.main.frag_students_all.*

class StudentsAllFragment : BaseVmFragment<StudentsAllVm>() {
    override val layoutId: Int = R.layout.frag_students_all
    override fun getVmClass(): Class<StudentsAllVm> = StudentsAllVm::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_students_all_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_students_frag_add -> {
                openStudentDetailAdd()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_students_all_rv.layoutManager = LinearLayoutManager(requireContext())
        frag_students_all_rv.adapter = StudentsAllAdapter { openStudentDetailEdit(it) }
        vm.requestStudentsAction.accept(Unit)
        frag_students_all_swrl.setOnRefreshListener { vm.refreshStudentsAction.accept(Unit) }
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            vm.studentsState
                .subscribe {
                    (frag_students_all_rv.adapter as? StudentsAllAdapter)?.let { adapter ->
                        adapter.students = it
                    }
                },
            vm.loadingState.subscribe {
                frag_students_all_swrl.isRefreshing = it
            }
        )
    }

    private fun openStudentDetailAdd() {
        val bundle = Bundle()
        bundle.putString(STUDENT_VIEW_TYPE, StudentDetailViewType.ADD.name)
        openFragment(R.id.nav_student_detail, bundle)
    }

    private fun openStudentDetailEdit(id: Long) {
        val bundle = Bundle()
        bundle.putLong(STUDENT_ID, id)
        bundle.putString(STUDENT_VIEW_TYPE, StudentDetailViewType.EDIT.name)
        openFragment(R.id.nav_student_detail, bundle)
    }

    enum class StudentDetailViewType {
        ADD,
        EDIT,
        EMPTY
    }
}