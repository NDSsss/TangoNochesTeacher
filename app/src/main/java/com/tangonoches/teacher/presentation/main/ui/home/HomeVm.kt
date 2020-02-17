package com.tangonoches.teacher.presentation.main.ui.home

import com.tangonoches.teacher.domain.repositories.ILoginRepository
import com.tangonoches.teacher.presentation.base.BaseVm
import javax.inject.Inject

class HomeVm @Inject constructor(
    loginRepository: ILoginRepository
): BaseVm() {
}