package com.amalitech.arms_mobile.core.utilities

interface UseCase<T, E> {

    suspend operator fun invoke(args: T? = null) : E

}