package com.example.marvelheroesapp

abstract class UseCase<Params : UseCase.RequestValues, Response : UseCase.ResponseValue> {

    var requestValues: Params? = null

    var useCaseCallback: UseCaseCallback<Response>? = null

    internal fun run() {
        executeUseCase(requestValues)
    }

    protected abstract fun executeUseCase(requestValues: Params?)

    interface RequestValues

    interface ResponseValue

    interface UseCaseCallback<R> {
        fun onSuccess(response: R)
        fun onError(t: Throwable)
    }
}