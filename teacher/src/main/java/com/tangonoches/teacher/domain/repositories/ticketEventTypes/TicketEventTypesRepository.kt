package com.tangonoches.teacher.domain.repositories.ticketEventTypes

import com.jakewharton.rxrelay2.BehaviorRelay
import com.tangonoches.teacher.data.models.TicketEventTypeModel
import com.tangonoches.teacher.domain.datasources.web.ticketEventTypes.ITicketEventTypesDataSource
import io.reactivex.Single

class TicketEventTypesRepository(
    private val ticketEventTypesDataSource: ITicketEventTypesDataSource
) : ITicketEventTypesRepository {

    private val ticketEventTypesState = BehaviorRelay.create<List<TicketEventTypeModel>>()

    override fun getTicketEventTypes(): Single<List<TicketEventTypeModel>> =
        if (ticketEventTypesState.hasValue().not()) {
            ticketEventTypesDataSource.getTicketEventTypes()
                .doOnSuccess { ticketEventTypesState.accept(it) }
        } else {
            Single.just(ticketEventTypesState.value!!)
        }
}