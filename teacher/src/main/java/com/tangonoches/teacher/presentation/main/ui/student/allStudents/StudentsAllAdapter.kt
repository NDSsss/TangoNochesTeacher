package com.tangonoches.teacher.presentation.main.ui.student.allStudents

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.StudentShortModel
import kotlinx.android.synthetic.main.item_student.view.*

class StudentsAllAdapter(
    private val detailListener: (Long) -> Unit,
    private val addTicketListener: (Long) -> Unit
) : RecyclerView.Adapter<StudentShortVh>() {

    var students: List<StudentShortModel> = listOf()
        set(value) {
            field = value
            Log.d("APP_TAG", "StudentsAllAdapter students value $value")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentShortVh =
        StudentShortVh(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_student,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: StudentShortVh, position: Int) {
        holder.bind(students[position], detailListener, addTicketListener)
    }
}

class StudentShortVh(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        student: StudentShortModel,
        detailListener: (Long) -> Unit,
        addTicketListener: (Long) -> Unit
    ) {
        Log.d("APP_TAG", "StudentShortVh bind student $student")
        itemView.item_student_name.text = student.name
        itemView.item_student_ticket_id.text = student.barcodeId.toString()
        itemView.item_student_edit.setOnClickListener { detailListener(student.id) }
        itemView.item_student_create_ticket.setOnClickListener { addTicketListener(student.id) }
    }
}