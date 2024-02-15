package com.amalitech.arms_mobile.core.interfaces

interface UseCase<T, E> {

    suspend fun execute(args: T? = null) : E

}