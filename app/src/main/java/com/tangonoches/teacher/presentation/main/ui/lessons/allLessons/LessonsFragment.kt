package com.tangonoches.teacher.presentation.main.ui.lessons.allLessons

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseVmFragment
import kotlinx.android.synthetic.main.frag_lessons.*

class LessonsFragment : BaseVmFragment<LessonsVm>() {
    override val layoutId: Int = R.layout.frag_lessons

    override fun getVmClass(): Class<LessonsVm> =
        LessonsVm::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_lessons_rv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter =
                LessonsAdapter {
                    vm.requestNextPageAction.accept(Unit)
                }
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
}