package com.vibuy.legacy.domain.executor

import io.reactivex.Scheduler

/**
 * Created by F.K. on 01.05.2019.
 *
 */

interface PostExecutionThread {
    val scheduler: Scheduler
}