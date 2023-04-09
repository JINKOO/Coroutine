package com.example.coroutineapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coroutineapp.presentation.login.LoginScreen
import com.example.coroutineapp.presentation.splash.SplashScreen
import com.example.coroutineapp.presentation.splash.SplashViewModel

sealed class NavDestinations(
    val route: String
) {
    object SplashScreen : NavDestinations("splash_screen")
    object LoginScreen : NavDestinations("login_screen")
    object HomeScreen : NavDestinations("home_screen")
}

@Composable
fun SampleSplashApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavDestinations.SplashScreen.route
    ) {
        composable(NavDestinations.SplashScreen.route) {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreen(
                viewModel =  viewModel,
                navigateToHome = {
                    navController.navigate(NavDestinations.HomeScreen.route)
                },
                navigateToLogin = {
                    navController.navigate(NavDestinations.LoginScreen.route)
                }
            )
        }

        composable(NavDestinations.LoginScreen.route) {
            LoginScreen()
        }

        composable(NavDestinations.HomeScreen.route) {

        }
    }
}