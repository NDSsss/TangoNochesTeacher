package com.tangonoches.teacher.presentation.main.ui.tickets.ticketsAll

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.TicketFullFilledModel
import com.tangonoches.teacher.domain.interactors.tickets.ITicketsInteractor
import com.tangonoches.teacher.presentation.base.BaseVm
import javax.inject.Inject

class TicketsAllVm @Inject constructor(
    private val ticketsInteractor: ITicketsInteractor
) : BaseVm() {

    val tickersState = BehaviorRelay.create<List<TicketFullFilledModel>>()

    val hasMorePagesState = BehaviorRelay.create<Boolean>()

    val requestNextPageAction = PublishRelay.create<Unit>()
    val refreshAction = PublishRelay.create<Unit>()

    private var nextPage = 1

    override fun createBinds() {
        super.createBinds()
        binds.addAll(
            ticketsInteractor.getTicketsPage(nextPage)
                .subLoading()
                .subWithDefaultError { tickets ->
                    tickersState.accept(tickets)
                    hasMorePagesState.accept(tickets.isNotEmpty())
                    nextPage++
                },
            requestNextPageAction.subscribe {
                ticketsInteractor.getTicketsPage(nextPage)
                    .subLoading()
                    .subWithDefaultError { tickets ->
                        tickersState.accept(tickersState.getValueOrThrowNPE().plus(tickets))
                        hasMorePagesState.accept(tickets.isNotEmpty())
                        nextPage++
                    }
            },
            refreshAction.subscribe {
                nextPage = 1
                binds.add(
                    ticketsInteractor.getTicketsPage(nextPage)
                        .subLoading()
                        .subWithDefaultError { tickets ->
                            tickersState.accept(tickets)
                            hasMorePagesState.accept(tickets.isNotEmpty())
                            nextPage++
                        }
                )
            }
        )
    }
}