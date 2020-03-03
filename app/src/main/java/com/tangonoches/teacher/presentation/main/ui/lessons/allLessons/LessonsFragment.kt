package com.tangonoches.teacher.presentation.main.ui.lessons.allLessons

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseVmFragment
import kotlinx.android.synthetic.main.frag_lessons.*

const val LESSON_DETAIL_ID = "LESSON_DETAIL_ID"
const val LESSON_DETAIL_VIEW_TYPE = "LESSON_DETAIL_VIEW_TYPE"

class LessonsFragment : BaseVmFragment<LessonsVm>() {
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
        inflater.inflate(R.menu.meny_lessons_frag, menu)
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
        openFragment(R.id.nav_lessons_detail)
    }

    private fun openEditLessonFragment(lessonId: Long) {
        Log.d("APP_TAG", "LessonsFragment openEditLessonFragment $lessonId")
        val bundle = Bundle()
        bundle.putLong(LESSON_DETAIL_ID, lessonId)
        bundle.putString(LESSON_DETAIL_VIEW_TYPE, LessonDetailViewType.EDIT.name)
        openFragment(R.id.nav_lessons_detail, bundle)
    }
}

enum class LessonDetailViewType {
    ADD,
    EDIT
}