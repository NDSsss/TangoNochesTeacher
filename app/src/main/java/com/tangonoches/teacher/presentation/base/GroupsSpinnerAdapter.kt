package com.tangonoches.teacher.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tangonoches.teacher.R
import com.tangonoches.teacher.data.models.GroupFullModel
import kotlinx.android.synthetic.main.item_spinner_dropdownt.view.*
import kotlinx.android.synthetic.main.item_spinner_main.view.*

class GroupsSpinnerAdapter(
    val selectionListener: (itemId: Long) -> Unit
) : BaseAdapter() {

    var groupsList: List<GroupFullModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val resultView = convertView
            ?: LayoutInflater.from(parent!!.context).inflate(
                R.layout.item_spinner_main,
                parent,
                false
            )
        resultView.item_spinner_main_tv.setText(groupsList[position].name)
        return resultView
    }


    override fun getItem(position: Int): Any = groupsList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = groupsList.size

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentModel = groupsList[position]
        val resultView = convertView ?: LayoutInflater.from(parent!!.context).inflate(
            R.layout.item_spinner_dropdownt,
            parent,
            false
        )
        resultView.setOnClickListener(null)
        resultView.item_spinner_dropdown_tv.setText(currentModel.name)
        resultView.item_spinner_dropdown_cb.isChecked = currentModel.isSelected
        resultView.setOnClickListener {
            selectionListener(currentModel.id)
        }
        return resultView
    }


}