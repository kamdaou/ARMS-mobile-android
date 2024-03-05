package com.amalitech.arms_mobile.core.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLeaveRepository(
        leaveRepository: com.amalitech.home.repositories.LeaveRepositoryImpl
    ): com.amalitech.home.repositories.LeaveRepository

    @Binds
    abstract fun bindCelebrationRepository(
        celebrationRepository: com.amalitech.home.repositories.CelebrationRepositoryImpl
    ): com.amalitech.home.repositories.CelebrationRepository
}

@Module
@InstallIn(SingletonComponent::class)
object ReposModule {
    @Singleton
    @Provides
    fun provideIoDispatchers(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}
