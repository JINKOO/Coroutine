package com.example.coroutineapp.domain.usecase

import com.example.coroutineapp.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

class GetUserLoginTokenUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {

    suspend fun getUserLoginToken(): Flow<String> {
        Timber.d("here")
        return userInfoRepository.fetchUserLoginToken()
    }


    suspend fun storeUserLoginToken(token: String) {
        userInfoRepository.storeUserLoginToken(token)
    }
}