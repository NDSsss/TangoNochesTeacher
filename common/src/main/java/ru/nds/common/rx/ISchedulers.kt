package ru.nds.common.rx

import io.reactivex.Scheduler

interface ISchedulers {
    fun getIoScheduler(): Scheduler
    fun getMainThread(): Scheduler
}