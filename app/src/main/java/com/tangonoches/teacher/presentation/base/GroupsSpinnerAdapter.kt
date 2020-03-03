package com.tangonoches.teacher.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.MenuPopupWindow
import com.tangonoches.teacher.R
import kotlinx.android.synthetic.main.item_spinner_dropdownt.view.*
import kotlinx.android.synthetic.main.item_spinner_main.view.*

data class AdapterDto<Model : Any>(
    val items: List<Model>,
    val selectedId: Long,
    val selectedPosition: Int = 0
) {
    fun newItemSelected(newId: Long, newPosition: Int): AdapterDto<Model> =
        this.copy(selectedId = newId, selectedPosition = newPosition)
}

class GroupsSpinnerAdapter<Model : Any>(
    var adapterDto: AdapterDto<Model>,
    val nameGenerator: (Model) -> String,
    val idGenerator: (Model) -> Long,
    val selectionListener: (itemId: Long, position: Int) -> Unit
) : BaseAdapter() {

    fun setNewAdapterDto(newDto: AdapterDto<Model>) {
        adapterDto = newDto
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val resultView = convertView
            ?: LayoutInflater.from(parent!!.context).inflate(
                R.layout.item_spinner_main,
                parent,
                false
            )
        resultView.item_spinner_main_tv.setText(nameGenerator(adapterDto.items[position]))
        return resultView
    }


    override fun getItem(position: Int): Any = adapterDto.items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = adapterDto.items.size

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentModel = adapterDto.items[position]
        val resultView = convertView ?: LayoutInflater.from(parent!!.context).inflate(
            R.layout.item_spinner_dropdownt,
            parent,
            false
        )
        resultView.setOnClickListener(null)
        resultView.item_spinner_dropdown_tv.setText(nameGenerator(currentModel))
        resultView.item_spinner_dropdown_cb.isChecked =
            adapterDto.selectedId == idGenerator(currentModel)
        resultView.setOnClickListener {
            selectionListener(idGenerator(currentModel), position)
        }
        return resultView
    }


}