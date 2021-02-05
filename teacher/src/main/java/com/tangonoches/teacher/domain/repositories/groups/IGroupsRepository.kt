package com.tangonoches.teacher.domain.repositories.groups

import com.tangonoches.teacher.data.models.GroupFullModel
import io.reactivex.Single

interface IGroupsRepository {
    fun getAllGroups():Single<List<GroupFullModel>>
}