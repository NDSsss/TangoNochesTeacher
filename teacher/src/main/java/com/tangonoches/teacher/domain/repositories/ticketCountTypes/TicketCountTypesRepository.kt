package com.tangonoches.teacher.domain.repositories.ticketCountTypes

import com.jakewharton.rxrelay2.BehaviorRelay
import com.tangonoches.teacher.data.models.TicketCountTypeModel
import com.tangonoches.teacher.domain.datasources.web.ticketCountTypes.ITicketCountTypesDataSource
import io.reactivex.Single

class TicketCountTypesRepository(
    private val ticketCountTypesDataSource: ITicketCountTypesDataSource
): ITicketCountTypesRepository {

    private val ticketCountTypesState = BehaviorRelay.create<List<TicketCountTypeModel>>()

    override fun getTicketCountTypes(): Single<List<TicketCountTypeModel>> =
        if(ticketCountTypesState.hasValue().not()){
            ticketCountTypesDataSource.getTicketCountTypes()
                .doOnSuccess { ticketCountTypesState.accept(it) }
        } else {
            Single.just(ticketCountTypesState.value!!)
        }
}