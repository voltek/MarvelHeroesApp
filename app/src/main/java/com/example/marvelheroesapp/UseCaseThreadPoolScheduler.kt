package com.example.marvelheroesapp

import android.os.Handler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class UseCaseThreadPoolScheduler : UseCaseScheduler {

    private val handler = Handler()

    private val threadPoolExecutor: ThreadPoolExecutor =
            ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT.toLong(),
                    TimeUnit.SECONDS,
                    ArrayBlockingQueue(POOL_SIZE))

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(response: V,
                                                            useCaseCallback: UseCase.UseCaseCallback<V>) {
        handler.post { useCaseCallback.onSuccess(response) }
    }

    override fun <V : UseCase.ResponseValue> onError(useCaseCallback: UseCase.UseCaseCallback<V>,
                                                     t: Throwable) {
        handler.post { useCaseCallback.onError(t) }
    }

    companion object {
        private const val POOL_SIZE = 2
        private const val MAX_POOL_SIZE = 4
        private const val TIMEOUT = 30
    }
}