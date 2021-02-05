package ru.nds.teacher.presentation.login

import com.jakewharton.rxrelay2.BehaviorRelay
import ru.nds.core.presentation.base.BaseVm
import ru.nds.teacher.login.domain.interactor.ILoginInteractor
import ru.nds.teacher.presentation.model.toModel
import javax.inject.Inject

class LoginVm @Inject constructor(
    private val loginRepository: ILoginInteractor
) : BaseVm() {
    val loginEvent = BehaviorRelay.create<Pair<String, String>>()

    val loginSuccessState = BehaviorRelay.create<Unit>()

    override fun createBinds() {
        super.createBinds()
        binds.add(
            loginEvent.subscribe { event ->
                binds.add(
                    loginRepository.login(
                        event.first, event.second
                    )
                        //TODO: just for example of clean architecture
                        .map { it.toModel() }
                        .subWithDefaultError {
                            //TODO: add navigation
                            loginSuccessState.accept(Unit)
                        }
                )
            }
        )
    }
}