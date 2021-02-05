package com.tangonoches.teacher.domain.datasources.web.groups

import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.responses.groups.toModel
import com.tangonoches.teacher.domain.services.GroupsService
import io.reactivex.Single
import ru.nds.common.rx.ISchedulers
import ru.nds.common.rx.utils.subToThreads

class GroupsDataSource(
    private val schedulers: ISchedulers,
    private val groupsService: GroupsService
) : IGroupsDataSource {
    override fun getAllGroups(): Single<List<GroupFullModel>> =
        groupsService.getAllGroups().map { list -> list.map { dto -> dto.toModel() } }
            .subToThreads(schedulers)
}