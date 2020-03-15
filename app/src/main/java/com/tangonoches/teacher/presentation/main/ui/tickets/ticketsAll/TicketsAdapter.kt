package com.tangonoches.teacher.presentation.main.ui.tickets.ticketsAll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.TicketFullFilledModel
import kotlinx.android.synthetic.main.item_ticket_full.view.*
import java.text.SimpleDateFormat
import java.util.*

class TicketsAdapter(
    private val nextPageListener: () -> Unit
) : RecyclerView.Adapter<TicketVh>() {

    var tickets: List<TicketFullFilledModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var hasMorePages: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketVh =
        TicketVh(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_ticket_full,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(holder: TicketVh, position: Int) {
        holder.bind(tickets[position])
        if (hasMorePages && position == tickets.size - 3) {
            nextPageListener()
        }
    }
}

class TicketVh(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(ticket: TicketFullFilledModel) {
        itemView.item_ticket_full_student_name.text = ticket.student.name
        itemView.item_ticket_full_teacher_name.text = ticket.teacher.name
        itemView.item_ticket_full_date.text = ticket.ticketStartDate.toTicketStartFormat()
            .plus(ticket.ticketEndDate.toTicketEndFormat())
        itemView.item_ticket_full_count.text = ticket.ticketCountType.name
        itemView.item_ticket_full_event.text = ticket.ticketEventType.name
    }
}

fun Date.toTicketStartFormat(): String =
    SimpleDateFormat("yyyy.MM.dd - ").format(this)

fun Date.toTicketEndFormat(): String =
    SimpleDateFormat("MM.dd").format(this)
