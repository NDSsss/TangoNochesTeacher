package ru.nds.common.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ISchedulers {
    fun getIoScheduler(): Scheduler
    fun getMainThread(): Scheduler
}