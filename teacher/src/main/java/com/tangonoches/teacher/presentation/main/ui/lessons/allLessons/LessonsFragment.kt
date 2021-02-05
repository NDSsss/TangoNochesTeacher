package com.tangonoches.teacher.presentation.main.ui.lessons.allLessons

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseTeacherFragment
import kotlinx.android.synthetic.main.frag_lessons.*

const val LESSON_DETAIL_ID = "LESSON_DETAIL_ID"
const val LESSON_DETAIL_VIEW_TYPE = "LESSON_DETAIL_VIEW_TYPE"

class LessonsFragment : BaseTeacherFragment<LessonsVm>() {
    override val layoutId: Int = R.layout.frag_lessons

    override fun getVmClass(): Class<LessonsVm> =
        LessonsVm::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_lessons_rv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter =
                LessonsAdapter(
                    requestNexPage = { vm.requestNextPageAction.accept(Unit) },
                    openDetail = { lessonId -> openEditLessonFragment(lessonId) }
                )
        }
        vm.requestLessonsAction.accept(Unit)
        frag_lessons_swipe.setOnRefreshListener { vm.lessonsRefreshAction.accept(Unit) }
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            vm.allLessonsValue.subscribe { newLessons ->
                (frag_lessons_rv.adapter as? LessonsAdapter).let {
                    it?.addLessons(newLessons)
                }
            },
            vm.lessonsLeftEffect.subscribe { lessonsLeft ->
                (frag_lessons_rv.adapter as? LessonsAdapter).let {
                    it?.lessonsIsOver(lessonsLeft)
                }
            },
            vm.groupsValue.subscribe { newGroups ->
                (frag_lessons_rv.adapter as? LessonsAdapter).let {
                    it?.addGroups(newGroups)
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_lessons_all_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_lessons_frag_add -> {
                openAddLessonFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openAddLessonFragment() {
        val bundle = Bundle()
        bundle.putString(LESSON_DETAIL_VIEW_TYPE, LessonDetailViewType.ADD.name)
//        openFragment(R.id.nav_lessons_detail)
        findNavController().navigate(R.id.nav_lessons_detail, bundle)
    }

    private fun openEditLessonFragment(lessonId: Long) {
        Log.d("APP_TAG", "LessonsFragment openEditLessonFragment $lessonId")
        val bundle = Bundle()
        bundle.putLong(LESSON_DETAIL_ID, lessonId)
        bundle.putString(LESSON_DETAIL_VIEW_TYPE, LessonDetailViewType.EDIT.name)
        findNavController().navigate(R.id.nav_lessons_detail, bundle)
//        openFragment(R.id.nav_lessons_detail, bundle)
    }

    override fun startLoading() {
        frag_lessons_swipe.isRefreshing = true
    }

    override fun completeLoading() {
        frag_lessons_swipe.isRefreshing = false
    }
}

enum class LessonDetailViewType {
    ADD,
    EDIT,
    EMPTY
}