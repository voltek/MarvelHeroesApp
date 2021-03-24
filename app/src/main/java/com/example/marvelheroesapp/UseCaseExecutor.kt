package com.example.marvelheroesapp

class UseCaseExecutor(private val useCaseScheduler: UseCaseScheduler) {

    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(
            useCase: UseCase<T, R>, values: T,
            callback: UseCase.UseCaseCallback<R>) {
        useCase.requestValues = values
        useCase.useCaseCallback = UiCallbackWrapper(callback, this)

        useCaseScheduler.execute { useCase.run() }
    }

    private fun <V : UseCase.ResponseValue> notifyResponse(response: V,
                                                           useCaseCallback: UseCase.UseCaseCallback<V>) {
        useCaseScheduler.notifyResponse(response, useCaseCallback)
    }

    private fun <V : UseCase.ResponseValue> notifyError(useCaseCallback: UseCase.UseCaseCallback<V>,
                                                        t: Throwable) {
        useCaseScheduler.onError(useCaseCallback, t)
    }

    private class UiCallbackWrapper<V : UseCase.ResponseValue>(
            private val callback: UseCase.UseCaseCallback<V>,
            private val useCaseExecutor: UseCaseExecutor) : UseCase.UseCaseCallback<V> {

        override fun onSuccess(response: V) {
            useCaseExecutor.notifyResponse(response, callback)
        }

        override fun onError(t: Throwable) {
            useCaseExecutor.notifyError(callback, t)
        }
    }

    companion object {
        private var INSTANCE: UseCaseExecutor? = null

        fun getInstance(): UseCaseExecutor {
            if (INSTANCE == null) INSTANCE = UseCaseExecutor(UseCaseThreadPoolScheduler())
            return INSTANCE as UseCaseExecutor
        }
    }
}