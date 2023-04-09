package com.example.coroutineapp.data.di

import com.example.coroutineapp.data.repository.UserInfoRepositoryImpl
import com.example.coroutineapp.domain.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class BindRepository {

    @Binds
    abstract fun bindUserInfoRepository(
        userInfoRepositoryImpl: UserInfoRepositoryImpl
    ): UserInfoRepository
}