package com.example.coroutineapp.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import timber.log.Timber

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = viewModel(),
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    val splashUiState by viewModel.splashUiState.collectAsStateWithLifecycle()
    Timber.d("splashUiState :: $splashUiState")

    // navcontroller를 사용해 navigate를 할 때, recompositiond이 계속 발생되어
    // launcehdeffect사용해 nvaigate로직 이동
    LaunchedEffect(key1 = Unit) {
        Timber.d("launched effect :: $splashUiState")
        if (splashUiState.userToken.isNotEmpty()) {
            // 1. A 상황 token이 존재하는 경우(0.7초 걸림), 1초간 SplashScreen
            // 2. A 상황 token이 존재하는 경우(1.5초 걸림), 1.5초 SplashScreen
            if(viewModel.getTokenJob?.isActive == true) {
                Timber.d("isActivie")
            } else {
                navigateToHome()
            }
        } else {
            // 1. B 상황 token이 존재하는 경우, 1초간 SplashScreen
            // 2. B 상황 token이 존재하는 경우, 1초간 SplashScreen
            Timber.d("User Token is Empty")
            navigateToLogin()
        }
    }

    // Splash Image
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
    }
}