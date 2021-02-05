package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.getSelectedPosition
import com.tangonoches.teacher.presentation.base.BaseTeacherFragment
import com.tangonoches.teacher.presentation.base.GroupsSpinnerAdapter
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LESSON_DETAIL_ID
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LESSON_DETAIL_VIEW_TYPE
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType
import com.tangonoches.teacher.presentation.view.chips.StudentsChipsVm
import com.tangonoches.teacher.presentation.view.chips.TeacherChipsVm
import kotlinx.android.synthetic.main.frag_lesson_detail.*

class LessonDetailFragment : BaseTeacherFragment<LessonDetailVm>() {

    override val layoutId: Int = R.layout.frag_lesson_detail

    override fun getVmClass(): Class<LessonDetailVm> =
        LessonDetailVm::class.java

    private val teachersChipsWidgetVm: TeacherChipsVm by lazy {
        ViewModelProviders.of(this, vmFactoryWrapper.factory)
            .get(TeacherChipsVm::class.java)
    }

    private val studentsChipsWidgetVm: StudentsChipsVm by lazy {
        ViewModelProviders.of(this, vmFactoryWrapper.factory)
            .get(StudentsChipsVm::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (arguments != null) {
            arguments?.let {
                it.getLong(LESSON_DETAIL_ID, -1L).let { id ->
                    if (id != -1L) {
                        vm.argLessonId.accept(id)
                    }
                }
                it.getString(LESSON_DETAIL_VIEW_TYPE)?.let { viewType ->
                    vm.argViewType.accept(LessonDetailViewType.valueOf(viewType))
                }
            }
        } else {
            vm.argViewType.accept(LessonDetailViewType.EMPTY)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_lesson_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_lesson_detail_delete -> {
                vm.deleteAction.accept(Unit)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_lesson_detail_teachers_chips.initVm(teachersChipsWidgetVm, firstViewCreate)
        frag_lesson_detail_students_chips.initVm(studentsChipsWidgetVm, firstViewCreate)
        frag_lesson_detail_teachers_chips.hideScan()
        frag_lesson_detail_group_sp.adapter = GroupsSpinnerAdapter { itemId ->
            vm.groupSelectedAction.accept(itemId.id)
        }
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            vm.lessonNameRelay.subscribe { name ->
                frag_lesson_detail_name_et.setText(name)
            },
            vm.groupsRelay.subscribe { groups ->
                (frag_lesson_detail_group_sp.adapter as? GroupsSpinnerAdapter)?.let { adapter ->
                    adapter.groupsList = groups
                }
                val selectedIndex = groups.getSelectedPosition()
                if (selectedIndex > -1) {
                    frag_lesson_detail_group_sp.setSelection(selectedIndex)
                }
                closeSpinner(frag_lesson_detail_group_sp)
            },
            frag_lesson_detail_save_btn.clicks().subscribe {
                vm.saveAction.accept(Unit)
            },
            frag_lesson_detail_name_et.textChanges().subscribe {
                vm.nameChagedAction.accept(it.toString())
            },
            frag_lesson_detail_students_chips.openScanClicks().subscribe {
                findNavController().navigate(R.id.nav_student_scan)
//                openFragment(R.id.nav_student_scan)
            }
        )
    }
}

fun closeSpinner(spinner: Spinner) {
    try {
        val method = Spinner::class.java.getDeclaredMethod("onDetachedFromWindow")
        method.isAccessible = true
        method.invoke(spinner)
    } catch (e: Exception) {

    }
}