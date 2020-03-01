package com.tangonoches.teacher.presentation.main.ui.lessons.lessonDetail

import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseVmFragment

class LessonDetailFragment : BaseVmFragment<LessonDetailVm>() {
    override val layoutId: Int = R.layout.frag_lessons

    override fun getVmClass(): Class<LessonDetailVm> =
        LessonDetailVm::class.java
}