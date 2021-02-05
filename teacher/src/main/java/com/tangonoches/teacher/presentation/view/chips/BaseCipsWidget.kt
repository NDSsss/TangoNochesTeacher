package com.tangonoches.teacher.presentation.view.chips

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.view.clicks
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.view.base.BasesWidget
import io.reactivex.Observable
import kotlinx.android.synthetic.main.view_chip.view.*
import kotlinx.android.synthetic.main.wigget_chips.view.*

class BaseCipsWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BasesWidget<BaseChipsWidgetVm>(context, attrs, defStyleAttr) {
    override fun inflate(context: Context) {
        addView(View.inflate(context, R.layout.wigget_chips, null))
        widget_chips_items_rv.layoutManager = LinearLayoutManager(context)
        widget_chips_items_rv.adapter =
            BaseChipsWidgetItemsAdapter { vm.itemSelectedAction.accept(it) }
    }

    fun hideScan() {
        widget_chips_scan_iv.visibility = View.GONE
    }

    override fun createVmBinds() {
        super.createVmBinds()
        rxBinds.addAll(
            widget_chips_list_visibility_btn.clicks().subscribe {
                vm.itemsVisibilityToggleAction.accept(Unit)
            },
            vm.itemsVisibleState.subscribe { isVisible ->
                if (isVisible) {
                    View.VISIBLE
                } else {
                    View.GONE
                }.apply {
                    widget_chips_items_rv.visibility = this
                    widget_chips_search_et.visibility = this
                }
            },
            vm.chipsState.subscribe { list ->
                val inflater = LayoutInflater.from(context)
                widget_chips_flex.removeAllViews()
                list.forEach { item ->
                    val chip = inflater.inflate(R.layout.view_chip, null)
                    chip.tvChip.text = item.toString()
                    chip.ivClose.setOnClickListener {
                        vm.itemSelectedAction.accept(
                            ItemSelectedDto(
                                item.id,
                                false
                            )
                        )
                    }
                    widget_chips_flex.addView(chip)
                }
            },
            vm.itemsState.subscribe { list ->
                (widget_chips_items_rv.adapter as BaseChipsWidgetItemsAdapter).items = list
            }
        )
    }

    fun openScanClicks(): Observable<Unit> {
        return widget_chips_scan_iv.clicks()
    }
}