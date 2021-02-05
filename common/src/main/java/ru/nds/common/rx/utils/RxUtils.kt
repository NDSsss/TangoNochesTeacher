package ru.nds.common.rx.utils

import io.reactivex.Completable
import io.reactivex.Single
import ru.nds.common.rx.ISchedulers


fun <T : Any> Single<T>.subToThreads(schedulers: ISchedulers): Single<T> = this
    .subscribeOn(schedulers.getIoScheduler())
    .observeOn(schedulers.getMainThread())


fun Completable.subToThreads(schedulers: ISchedulers): Completable = this
    .subscribeOn(schedulers.getIoScheduler())
    .observeOn(schedulers.getMainThread())