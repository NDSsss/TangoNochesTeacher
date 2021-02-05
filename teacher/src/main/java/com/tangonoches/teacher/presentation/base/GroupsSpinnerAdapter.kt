package com.tangonoches.teacher.presentation.base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.view.chips.BaseChipItem
import kotlinx.android.synthetic.main.item_spinner_dropdownt.view.*
import kotlinx.android.synthetic.main.item_spinner_main.view.*

class GroupsSpinnerAdapter(
    val selectionListener: (item: BaseChipItem) -> Unit
) : BaseAdapter() {

    var groupsList: List<BaseChipItem> = listOf()
        set(value) {
            Log.d("APP_TAG","GroupsSpinnerAdapter groupsList $value")
            field = value
            notifyDataSetChanged()
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("APP_TAG","GroupsSpinnerAdapter getView")
        val resultView = convertView
            ?: LayoutInflater.from(parent!!.context).inflate(
                R.layout.item_spinner_main,
                parent,
                false
            )
        resultView.item_spinner_main_tv.setText(groupsList[position].toString())
        return resultView
    }


    override fun getItem(position: Int): Any = groupsList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = groupsList.size

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("APP_TAG","GroupsSpinnerAdapter getDropDownView")
        val currentModel = groupsList[position]
        val resultView = convertView ?: LayoutInflater.from(parent!!.context).inflate(
            R.layout.item_spinner_dropdownt,
            parent,
            false
        )
        resultView.setOnClickListener(null)
        resultView.item_spinner_dropdown_tv.setText(currentModel.toString())
        resultView.item_spinner_dropdown_cb.isChecked = currentModel.isSelected
        resultView.setOnClickListener {
            selectionListener(currentModel)
        }
        return resultView
    }


}