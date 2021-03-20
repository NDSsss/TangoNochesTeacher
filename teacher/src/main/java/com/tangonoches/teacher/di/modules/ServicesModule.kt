package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.services.*
import org.koin.dsl.module
import retrofit2.Retrofit

val monolitServicesModule = module {
    factory<LessonsService> {
        val retrofit: Retrofit = get<Retrofit>()
        retrofit.create(LessonsService::class.java)
    }
    factory<GroupsService> {
        val retrofit: Retrofit = get<Retrofit>()
        retrofit.create(GroupsService::class.java)
    }
    factory<TicketEventTypesService> {
        val retrofit: Retrofit = get<Retrofit>()
        retrofit.create(TicketEventTypesService::class.java)
    }
    factory<TicketCountTypesService> {
        val retrofit: Retrofit = get<Retrofit>()
        retrofit.create(TicketCountTypesService::class.java)
    }
    factory<StudentsService> {
        val retrofit: Retrofit = get<Retrofit>()
        retrofit.create(StudentsService::class.java)
    }
    factory<TeachersService> {
        val retrofit: Retrofit = get<Retrofit>()
        retrofit.create(TeachersService::class.java)
    }
    factory<TicketsService> {
        val retrofit: Retrofit = get<Retrofit>()
        retrofit.create(TicketsService::class.java)
    }
}