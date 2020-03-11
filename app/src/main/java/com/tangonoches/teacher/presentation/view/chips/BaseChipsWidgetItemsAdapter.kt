package com.tangonoches.teacher.presentation.view.chips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allyants.chipview.BaseChipItem
import com.tangonoches.teacher.R
import kotlinx.android.synthetic.main.item_chip_list_item.view.*

class BaseChipsWidgetItemsAdapter : RecyclerView.Adapter<ChipItemVh>() {

    var items: List<BaseChipItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipItemVh =
        ChipItemVh(LayoutInflater.from(parent.context).inflate(R.layout.item_chip_list_item, null))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ChipItemVh, position: Int) {
        holder.bind(items[position])
    }
}

class ChipItemVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: BaseChipItem) {
        itemView.item_chip_list_item_cb.setText(item.toString())
        itemView.item_chip_list_item_cb.isChecked = item.isSelected
        itemView.item_chip_list_item_cb.setOnCheckedChangeListener { buttonView, isChecked -> }
    }
}