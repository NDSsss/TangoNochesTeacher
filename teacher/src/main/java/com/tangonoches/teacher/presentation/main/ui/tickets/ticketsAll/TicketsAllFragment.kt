package com.tangonoches.teacher.presentation.main.ui.tickets.ticketsAll

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tangonoches.teacher.R
import kotlinx.android.synthetic.main.frag_tickets_all.*
import ru.nds.core.presentation.base.BaseVmFragment

class TicketsAllFragment : BaseVmFragment<TicketsAllVm>(TicketsAllVm::class) {

    override val layoutId: Int = R.layout.frag_tickets_all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_tickets_all, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_tickets_all_add -> {
                openTicketCreateFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_tickets_all_rv.layoutManager = LinearLayoutManager(requireContext())
        frag_tickets_all_rv.adapter = TicketsAdapter {
            vm.requestNextPageAction.accept(Unit)
        }
        frag_tickets_all_swipe.setOnRefreshListener {
            vm.refreshAction.accept(Unit)
        }
        frag_tickets_all_search.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                vm.searchQueryState.accept(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm.searchQueryState.accept(newText ?: "")
                return true
            }

        })
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            vm.ticketsFiltredState.subscribe { tickets ->
                (frag_tickets_all_rv.adapter as? TicketsAdapter)?.let { adapter ->
                    adapter.tickets = tickets
                }
            },
            vm.hasMorePagesState.subscribe { hasMorePages ->
                (frag_tickets_all_rv.adapter as? TicketsAdapter)?.let { adapter ->
                    adapter.hasMorePages = hasMorePages
                }
            }
        )
    }

    override fun startLoading() {
        frag_tickets_all_swipe.isRefreshing = true
    }

    override fun completeLoading() {
        frag_tickets_all_swipe.isRefreshing = false
    }

    private fun openTicketCreateFragment() {
        findNavController().navigate(R.id.nav_ticket_create)
    }
}