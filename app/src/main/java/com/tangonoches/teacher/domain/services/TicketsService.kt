package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.tickets.TicketFullClearDto
import com.tangonoches.teacher.data.responses.tickets.TicketCreateDto
import com.tangonoches.teacher.data.responses.tickets.TicketsResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TicketsService {

    @POST("teacher/allTickets")
    @FormUrlEncoded
    fun getTicketsPage(@Field("page") page: Int, @Field("count_on_page") countOnPage: Int): Single<TicketsResponse>

    @POST("teacher/ticketById")
    @FormUrlEncoded
    fun getTicketById(@Field("id") id: Long): Single<TicketFullClearDto>

    @POST("teacher/registerTicket")
    fun createTicket(@Body ticket: TicketCreateDto): Completable

}