package com.tangonoches.teacher.domain.datasources.web.groups

import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.responses.groups.toModel
import com.tangonoches.teacher.domain.datasources.web.login.subToThreads
import com.tangonoches.teacher.domain.services.GroupsService
import io.reactivex.Single

class GroupsDataSource(
    private val groupsService: GroupsService
) : IGroupsDataSource {
    override fun getAllGroups(): Single<List<GroupFullModel>> =
        groupsService.getAllGroups().map { list -> list.map { dto -> dto.toModel() } }.subToThreads()
}