package com.example.notifyme.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notifyme.presentation.screens.LoginScreen
import com.example.notifyme.presentation.screens.RegScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") { //NavHost отвечает за настройку маршрутов
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegScreen(navController) } // Добавляем маршрут для экрана регистрации
        composable("main") { MainScreen() }
    }
}


