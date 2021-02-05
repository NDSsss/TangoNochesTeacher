package com.tangonoches.teacher.domain.repositories.groups

import com.jakewharton.rxrelay2.BehaviorRelay
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.domain.datasources.web.groups.IGroupsDataSource
import io.reactivex.Single

class GroupsRepository(
    private val groupsDataSource: IGroupsDataSource
) : IGroupsRepository {
    private val groupsRelay = BehaviorRelay.create<List<GroupFullModel>>()
    override fun getAllGroups(): Single<List<GroupFullModel>> =
        if (groupsRelay.hasValue()) {
            Single.just(groupsRelay.value)
        } else {
            updateGroups()
        }

    private fun updateGroups(): Single<List<GroupFullModel>> =
        groupsDataSource.getAllGroups().doOnSuccess { list -> groupsRelay.accept(list) }
}