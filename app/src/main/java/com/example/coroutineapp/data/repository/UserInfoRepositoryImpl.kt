package com.example.coroutineapp.data.repository

import com.example.coroutineapp.data.local.datasource.LocalData
import com.example.coroutineapp.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val localData: LocalData
): UserInfoRepository {

    override val userToken: Flow<String> = localData.loginTokenFlow

    override suspend fun fetchUserLoginToken(): Flow<String> {
        return userToken
    }

    override suspend fun storeUserLoginToken(token: String) {
        localData.setLoginToken(token)
    }
}