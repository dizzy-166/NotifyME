package com.example.notifyme.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.notifyme.R

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }

    // Анимация появления + поворот
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )

        // Анимация поворота: немного наклоняем и возвращаем назад
        rotation.animateTo(
            targetValue = 15f, // Наклон в 15 градусов
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        rotation.animateTo(
            targetValue = -15f, // Обратный наклон
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        rotation.animateTo(
            targetValue = 0f, // Возвращение в исходное положение
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )

        delay(2000) // Задержка перед переходом
        navController.navigate("login") {  // Переход на экран авторизации
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splashscreenimage),
            contentDescription = "splashscreenimage",
            modifier = Modifier
                .scale(scale.value)
                .graphicsLayer(rotationZ = rotation.value) // Применяем поворот
        )
    }
}
