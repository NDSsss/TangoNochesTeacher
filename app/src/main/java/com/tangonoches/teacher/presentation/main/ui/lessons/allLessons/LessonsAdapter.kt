package com.tangonoches.teacher.presentation.main.ui.lessons.allLessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import kotlinx.android.synthetic.main.item_lesson_short.view.*

class LessonsAdapter(
    private val requestNexPage: () -> Unit,
    private val openDetail: (lessonId: Long) -> Unit
) :
    RecyclerView.Adapter<LessonShortVh>() {

    private var lessons = listOf<LessonShortModel>()
    private var groups = listOf<GroupFullModel>()

    private var hasMoreLessons = false

    fun addLessons(newLessons: List<LessonShortModel>) {
        lessons = newLessons
        notifyDataSetChanged()
    }

    fun addGroups(newGroups: List<GroupFullModel>) {
        groups = newGroups
        notifyDataSetChanged()
    }

    fun lessonsIsOver(lessonIsOver: Boolean) {
        hasMoreLessons = lessonIsOver
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonShortVh =
        LessonShortVh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_lesson_short, parent, false)
        )

    override fun getItemCount(): Int = lessons.size

    override fun onBindViewHolder(holder: LessonShortVh, position: Int) {
        holder.bind(
            lessons[position],
            groups.firstOrNull { group -> group.id == lessons[position].groupId },
            openDetail
        )
        if (position == lessons.size - 4 && hasMoreLessons) {
            requestNexPage.invoke()
        }
    }
}

class LessonShortVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        lessonShort: LessonShortModel,
        group: GroupFullModel?,
        detailListener: (lessonId: Long) -> Unit
    ) {
        itemView.item_lesson_short_name_tv.text = lessonShort.name
        itemView.item_lesson_short_date_tv.text = lessonShort.lessonDate
        itemView.item_lesson_short_detail_iv.setOnClickListener {
            detailListener(lessonShort.id)
        }
        group?.let { itemView.item_lesson_short_group_tv.text = it.name }
    }
}