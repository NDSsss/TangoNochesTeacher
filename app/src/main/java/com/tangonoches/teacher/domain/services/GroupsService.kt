package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.groups.GroupFullDto
import io.reactivex.Single
import retrofit2.http.GET

interface GroupsService {
    @GET("teacher/allGroups")
    fun getAllGroups():Single<List<GroupFullDto>>
}