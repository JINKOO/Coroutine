package com.example.coroutineapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineapp.domain.model.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isValidateId: Boolean = false,
    val isValidatePw: Boolean = false,
    val isAccessKySuccess: Boolean = false,
    val userInfo: UserInfo = UserInfo()
)

class LoginViewModel: ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    init {
        getUserInfo("jinkweonko", "123123")
    }

    //
    private fun verifyIdAndPassword(id: String, password: String) {
        // 새로운 코루틴을 생성하고, IO Thread에서 처리
        val deffered = viewModelScope.async {
            delay(1500)
            isValidate(id, password)
        }
    }

    private fun getAccessKey() {
        viewModelScope.launch {
            delay(1500)
            // acesskey check
        }
    }

    private fun getUserInfo(id: String, password: String) {
        verifyIdAndPassword(id, password)
        getAccessKey()
    }

    private fun isValidate(id: String, password: String): Boolean {
        return id == "jinkweonKo" && password == "123123"
    }
}