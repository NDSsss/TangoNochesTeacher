package com.tangonoches.teacher.domain.repositories.constants

const val DEFAULT_ID: Long = -1L

class ConstantsRepository: IConstantsRepository {
    override val itemsOnPage: Int
        get() = 10
}