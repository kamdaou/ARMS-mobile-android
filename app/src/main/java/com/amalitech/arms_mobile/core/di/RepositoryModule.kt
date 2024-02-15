package com.amalitech.arms_mobile.core.di

import com.amalitech.arms_mobile.data.repositories.CelebrationRepositoryImpl
import com.amalitech.arms_mobile.data.repositories.LeaveRepositoryImpl
import com.amalitech.arms_mobile.domain.respositories.CelebrationRepository
import com.amalitech.arms_mobile.domain.respositories.LeaveRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLeaveRepository(
        leaveRepository: LeaveRepositoryImpl
    ) : LeaveRepository

    @Binds
    abstract fun bindCelebrationRepository(
        celebrationRepository: CelebrationRepositoryImpl
    ) : CelebrationRepository
}