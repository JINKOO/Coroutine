package com.example.coroutineapp.presentation.splash

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coroutineapp.R
import kotlinx.coroutines.delay

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
            SplashScreen(navController = navController)
        }

        composable(NavDestinations.LoginScreen.route) {

        }

        composable(NavDestinations.HomeScreen.route) {

        }
    }
}

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleSplashApp()
        }
    }
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    LaunchedEffect(key1 = Unit) {
        // delay time을 customize
        delay(1000L)
        navController.navigate(NavDestinations.HomeScreen.route)
    }

    // Splash Image
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_border_all_24),
            contentDescription = null
        )
    }
}