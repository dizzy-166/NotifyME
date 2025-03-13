package com.example.notifyme.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.notifyme.R

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) } //масштаб
    val rotation = remember { Animatable(0f) } //угол поворота

    val text = "NotifyMe"
    val animatedText = remember { mutableStateOf("") }
    val splashColor = colorResource(id = R.color.Green_for_Splash)

    // Анимация появления + поворот при появлении экрана
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )

        // Анимация поворота
        rotation.animateTo(
            targetValue = 15f, // Наклон в 15 градусов
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        rotation.animateTo(
            targetValue = -15f, // Обратный наклон
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing ) //эффект плавного поворота
        )
        rotation.animateTo(
            targetValue = 0f, // Возвращение в исходное положение
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )

        // Анимация появления текста по одной букве
        text.forEachIndexed { index, char ->
            delay(150)
            animatedText.value = text.substring(0, index + 1)
        }

        delay(2000) // Задержка перед переходом
        navController.navigate("login") {  // Переход на экран авторизации
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), //заполнение фона белым
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splashscreenimage),
                contentDescription = "splashscreenimage",
                modifier = Modifier
                    .scale(scale.value)
                    .graphicsLayer(rotationZ = rotation.value) // Применяем поворот
            )
            Spacer(modifier = Modifier.height(32.dp)) // Увеличенный отступ между логотипом и текстом
            Text(
                text = animatedText.value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = splashColor,
                textAlign = TextAlign.Center
            )
        }
    }
}
