package com.tangonoches.teacher.presentation.main.ui.tickets.ticketsAll

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseVmFragment
import kotlinx.android.synthetic.main.frag_tickets_all.*

class TicketsAllFragment : BaseVmFragment<TicketsAllVm>() {

    override val layoutId: Int = R.layout.frag_tickets_all

    override fun getVmClass(): Class<TicketsAllVm> = TicketsAllVm::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_tickets_all_rv.layoutManager = LinearLayoutManager(requireContext())
        frag_tickets_all_rv.adapter = TicketsAdapter {
            vm.requestNextPageAction.accept(Unit)
        }
        frag_tickets_all_swipe.setOnRefreshListener {
            vm.refreshAction.accept(Unit)
        }
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            vm.tickersState.subscribe { tickets ->
                (frag_tickets_all_rv.adapter as? TicketsAdapter)?.let { adapter ->
                    adapter.tickets = tickets
                }
            },
            vm.hasMorePagesState.subscribe { hasMorePages ->
                (frag_tickets_all_rv.adapter as? TicketsAdapter)?.let { adapter ->
                    adapter.hasMorePages = hasMorePages
                }
            },
            vm.loadingState.subscribe { isLoading ->
                frag_tickets_all_swipe.isRefreshing = isLoading
            }
        )
    }
}