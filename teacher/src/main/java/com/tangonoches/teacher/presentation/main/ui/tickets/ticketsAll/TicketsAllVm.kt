package com.tangonoches.teacher.presentation.main.ui.tickets.ticketsAll

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.TicketFullFilledModel
import com.tangonoches.teacher.domain.interactors.tickets.ITicketsInteractor
import ru.nds.core.presentation.base.BaseVm
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TicketsAllVm(
    private val ticketsInteractor: ITicketsInteractor
) : BaseVm() {

    val tickersState = BehaviorRelay.create<List<TicketFullFilledModel>>()
    val ticketsFiltredState = BehaviorRelay.create<List<TicketFullFilledModel>>()

    val searchQueryState = BehaviorRelay.createDefault("")

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
            },
            ticketsInteractor.getRefreshObservable().subscribe {
                refreshAction.accept(it)
            },
            tickersState.subscribe { tickets ->
                val searchQuery = searchQueryState.getValueOrThrowNPE().toLowerCase()
                ticketsFiltredState.accept(tickets.filter {
                    it.student.name.toLowerCase().contains(searchQuery)
                            || it.student.barcodeId.toString().toLowerCase().contains(searchQuery)
                })
            },
            searchQueryState.debounce(500, TimeUnit.MILLISECONDS)
                .subscribe { query ->
                    val searchQuery = query.toLowerCase()
                    if (tickersState.hasValue()) {
                        val tickets = tickersState.getValueOrThrowNPE()
                        binds.add(
                            Single.fromCallable {
                                tickets.filter {
                                    it.student.name.toLowerCase().contains(searchQuery)
                                            || it.student.barcodeId.toString().toLowerCase().contains(
                                        searchQuery
                                    )
                                }
                            }
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subWithDefaultError { filtredList ->
                                    ticketsFiltredState.accept(filtredList)
                                }
                        )
                    }
                }
        )
    }
}