package com.amalitech.arms_mobile.core.di

import com.amalitech.arms_mobile.data.repositories.CelebrationRepositoryImpl
import com.amalitech.arms_mobile.data.repositories.LeaveRepositoryImpl
import com.amalitech.arms_mobile.domain.respositories.CelebrationRepository
import com.amalitech.arms_mobile.domain.respositories.LeaveRepository
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
        leaveRepository: LeaveRepositoryImpl
    ): LeaveRepository

    @Binds
    abstract fun bindCelebrationRepository(
        celebrationRepository: CelebrationRepositoryImpl
    ): CelebrationRepository
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
