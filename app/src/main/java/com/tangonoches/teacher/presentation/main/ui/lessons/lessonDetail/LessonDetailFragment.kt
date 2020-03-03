package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.presentation.base.AdapterDto
import com.tangonoches.teacher.presentation.base.BaseVmFragment
import com.tangonoches.teacher.presentation.base.GroupsSpinnerAdapter
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LESSON_DETAIL_ID
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LESSON_DETAIL_VIEW_TYPE
import com.tangonoches.teacher.presentation.main.ui.lessons.allLessons.LessonDetailViewType
import kotlinx.android.synthetic.main.frag_lesson_detail.*

class LessonDetailFragment : BaseVmFragment<LessonDetailVm>() {
    override val layoutId: Int = R.layout.frag_lesson_detail

    override fun getVmClass(): Class<LessonDetailVm> =
        LessonDetailVm::class.java

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

    override fun initEvents() {
        super.initEvents()
        frag_lesson_detail_group_sp.adapter = GroupsSpinnerAdapter<GroupFullModel>(
            AdapterDto(listOf(), 0),
            { groupFullModel -> groupFullModel.name },
            { groupFullModel -> groupFullModel.id },
            { itemId, position -> vm.groupSelectedAction.accept(Pair(itemId,position))}
        )
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
        )
    }
}

fun closeSpinner(spinner: Spinner){
    try {
        val method = Spinner::class.java.getDeclaredMethod("onDetachedFromWindow")
        method.isAccessible = true
        method.invoke(spinner)
    } catch (e:Exception){

    }
}