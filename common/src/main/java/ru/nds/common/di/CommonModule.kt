package ru.nds.common.di

import org.koin.dsl.module
import ru.nds.common.rx.ISchedulers
import ru.nds.common.rx.ISchedulersImpl


val commonModule = module {
    factory<ISchedulers> { ISchedulersImpl() }
}