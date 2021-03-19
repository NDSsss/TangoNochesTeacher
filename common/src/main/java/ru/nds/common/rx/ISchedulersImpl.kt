package ru.nds.common.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


internal class ISchedulersImpl: ISchedulers {
    override fun getIoScheduler(): Scheduler = Schedulers.io()

    override fun getMainThread(): Scheduler = AndroidSchedulers.mainThread()

}