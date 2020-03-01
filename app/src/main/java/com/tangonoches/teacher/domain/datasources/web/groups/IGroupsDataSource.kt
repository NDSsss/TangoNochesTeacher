package com.tangonoches.teacher.domain.datasources.web.groups

import com.tangonoches.teacher.data.models.GroupFullModel
import io.reactivex.Single

interface IGroupsDataSource {
    fun getAllGroups(): Single<List<GroupFullModel>>
}