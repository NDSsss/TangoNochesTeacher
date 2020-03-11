package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner
import androidx.lifecycle.ViewModelProviders
import com.allyants.chipview.SimpleChipAdapter
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.presentation.base.AdapterDto
import com.tangonoches.teacher.presentation.base.BaseVmFragment
import com.tangonoches.teacher.presentation.base.GroupsSpinnerAdapter
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LESSON_DETAIL_ID
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LESSON_DETAIL_VIEW_TYPE
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType
import com.tangonoches.teacher.presentation.view.chips.StudentsChipsVm
import com.tangonoches.teacher.presentation.view.chips.TeacherChipsVm
import kotlinx.android.synthetic.main.frag_lesson_detail.*

class LessonDetailFragment : BaseVmFragment<LessonDetailVm>() {

    private lateinit var teachersSimpleChipAdapter: SimpleChipAdapter
    private lateinit var studentsSimpleChipAdapter: SimpleChipAdapter

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
        arguments?.let {
            it.getString(LESSON_DETAIL_VIEW_TYPE)?.let { viewType ->
                Log.d("APP_TAG", "LessonDetailFragment LESSON_DETAIL_VIEW_TYPE $viewType")
                vm.argViewType.accept(LessonDetailViewType.valueOf(viewType))
            }
            it.getLong(LESSON_DETAIL_ID, -1L).let { id ->
                if (id != -1L) {
                    Log.d("APP_TAG", "LessonDetailFragment LESSON_DETAIL_ID $id")
                    vm.argLessonId.accept(id)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        vm.settTeachersChipsWidgetVm(teachersChipsWidgetVm)
//        vm.settStudentsChipsWidgetVm(studentsChipsWidgetVm)
//        frag_lesson_detail_teachers_chips.initVm(vm.teachersChipsWidgetVm, true)
//        frag_lesson_detail_students_chips.initVm(vm.studentsChipsWidgetVm, true)
        frag_lesson_detail_teachers_chips.initVm(teachersChipsWidgetVm, true)
        frag_lesson_detail_students_chips.initVm(studentsChipsWidgetVm, true)
    }

    override fun initEvents() {
        super.initEvents()
        frag_lesson_detail_group_sp.adapter = GroupsSpinnerAdapter<GroupFullModel>(
            AdapterDto(listOf(), 0),
            { groupFullModel -> groupFullModel.name },
            { groupFullModel -> groupFullModel.id },
            { itemId, position -> vm.groupSelectedAction.accept(Pair(itemId, position)) }
        )
//        teachersChipAdapter = TeachersChipAdapter()
//        frag_lesson_detail_teachers_chips.setAdapter(teachersChipAdapter)
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            vm.lessonRelay.subscribe { lesson ->
                Log.d("APP_TAG", "LessonDetailFragment createVmBinds $lesson")
                frag_lesson_detail_name_et.setText(lesson.name)
            },
//            vm.groupsRelay.subscribe {groups->
//                frag_lesson_detail_group_sp.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, groups.map { group-> group.name })
//            }
            vm.grpupsForAdapter.subscribe { adapterDto ->
                (frag_lesson_detail_group_sp.adapter as? GroupsSpinnerAdapter<GroupFullModel>)?.let { adapter ->
                    adapter.setNewAdapterDto(adapterDto)
                }
                frag_lesson_detail_group_sp.setSelection(adapterDto.selectedPosition)
                closeSpinner(frag_lesson_detail_group_sp)
            }
//            vm.teachersChipRelay.subscribe {
//                val javaArrayList = ArrayList<BaseChipItem>()
//                javaArrayList.addAll(it.map { teacher -> teacher.teacher })
//                teachersSimpleChipAdapter = SimpleChipAdapter(javaArrayList)
//                frag_lesson_detail_teachers_chips.setAdapter(teachersSimpleChipAdapter)
//                val javaChips = ArrayList<BaseChipItem>()
//                javaChips.addAll(it.filter { teacher -> teacher.isSelected }.map { teacher -> teacher.teacher })
//                teachersSimpleChipAdapter.chips = javaChips
//            },
//            vm.studentsChipRelay.subscribe{
//                val javaArrayList = ArrayList<BaseChipItem>()
//                javaArrayList.addAll(it.map { student -> student.student })
//                studentsSimpleChipAdapter = SimpleChipAdapter(javaArrayList)
//                frag_lesson_detail_students_chips.setAdapter(studentsSimpleChipAdapter)
//                val javaChips = ArrayList<BaseChipItem>()
//                javaChips.addAll(it.filter { teacher -> teacher.isSelected }.map { student -> student.student })
//                studentsSimpleChipAdapter.chips = javaChips
//            }
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