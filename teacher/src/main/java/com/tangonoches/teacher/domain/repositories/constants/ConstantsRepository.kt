package com.tangonoches.teacher.domain.repositories.constants

const val DEFAULT_ID: Long = -1L
const val ITEMS_ON_PAGE = 10

class ConstantsRepository: IConstantsRepository {
    override val itemsOnPage: Int
        get() = 10
}