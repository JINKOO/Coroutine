package com.example.coroutineapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineapp.domain.usecase.GetUserLoginTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class SplashUiState(
    val userToken: String = ""
)

sealed interface SplashEvent {
    object GetUserToken : SplashEvent
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserLoginTokenUseCase: GetUserLoginTokenUseCase
) : ViewModel() {

    private val _splashUiState = MutableStateFlow(SplashUiState())
    val splashUiState = _splashUiState.asStateFlow()

    var getTokenJob: Job? = null

    init {
        fetchUserTokenFromServer()
        getToken()
    }

    fun splashEvent(event: SplashEvent) {
        when(event) {
            is SplashEvent.GetUserToken -> {
                getToken()
            }
        }
    }

    private fun fetchUserTokenFromServer() {
        val result = "123123"
        storeUserLoginToken(result)
    }

    private fun storeUserLoginToken(token: String) {
        viewModelScope.launch {
            getUserLoginTokenUseCase.storeUserLoginToken(token)
        }
    }

    // DataStore에 저장된 Token 값을 가져오는 로직
    private fun getToken() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserLoginTokenUseCase.getUserLoginToken().collect { userToken ->
                delay(700)
                Timber.d("result :: $userToken")
                _splashUiState.update {
                    it.copy(
                        userToken = userToken
                    )
                }
            }
        }
    }
}