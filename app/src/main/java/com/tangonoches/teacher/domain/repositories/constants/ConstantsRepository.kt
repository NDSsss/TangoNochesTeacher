package com.tangonoches.teacher.domain.repositories.constants

class ConstantsRepository: IConstantsRepository {
    override val itemsOnPage: Int
        get() = 10
}