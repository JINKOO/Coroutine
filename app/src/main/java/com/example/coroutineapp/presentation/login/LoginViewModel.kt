package com.example.coroutineapp.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineapp.domain.model.UserInfo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val isValidateId: Boolean = false,
    val isValidatePw: Boolean = false,
    val isAccessKySuccess: Boolean = false,
    val userInfo: UserInfo = UserInfo(),
    val errorMessage: String = "",
    val loginSuccess: Boolean = false
)

class LoginViewModel: ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    private val savedId = "jinkweonko"
    private val savedPassWord = "123123"

    private fun verifyIdAndPassword(id: String, password: String) = viewModelScope.async {
        // 새로운 코루틴을 생성하고, IO Thread에서 처리
            delay(1000)
            Log.d("1111", "verifyIdAndPassword deffered")
            isValidate(id, password)
    }

    private fun getAccessKey() = viewModelScope.async {
            delay(1500)
            Log.d("1111", "getAccessKey: ")
            true
//            false
    }

    fun getUserInfo(id: String, password: String) {
        viewModelScope.launch {
            try {
                withTimeout(3000) {
                    val deferredValidate = verifyIdAndPassword(id, password)
                    val deferredAccessKey = getAccessKey()
                    if (!deferredValidate.await() || !deferredAccessKey.await()) {
                        _loginUiState.update {
                            it.copy(
                                loginSuccess = false
                            )
                        }
                    } else {
                        _loginUiState.update {
                            it.copy(
                                loginSuccess = true
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _loginUiState.update {
                    it.copy(errorMessage = e.message ?: "ERROR")
                }
            }
        }
    }

    private fun isValidate(id: String, password: String): Boolean {
        return id == savedId && password == savedPassWord
    }
}