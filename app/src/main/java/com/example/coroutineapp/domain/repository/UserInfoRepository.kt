package com.example.coroutineapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {
    val userToken: Flow<String>
    suspend fun fetchUserLoginToken(): Flow<String>
    suspend fun storeUserLoginToken(token: String)
}