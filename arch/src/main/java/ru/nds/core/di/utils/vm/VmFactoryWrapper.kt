package ru.nds.core.di.utils.vm

import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class VmFactoryWrapper {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
}