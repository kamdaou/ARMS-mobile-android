package com.amalitech.arms_mobile.core.utilities

interface AdapterInterface<T, E> {
    operator fun invoke(entity: T): E
}
