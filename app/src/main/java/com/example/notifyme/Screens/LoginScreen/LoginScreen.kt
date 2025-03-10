package com.example.notifyme.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(navController: NavController) {
    // Поля для ввода данных
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // Логика авторизации
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Поле для ввода Email
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .border(1.dp, Color.Gray)
                .padding(12.dp),
            singleLine = true,
            label = { Text("Email") } // Подсказка для поля email
        )

        // Поле для ввода пароля
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .border(1.dp, Color.Gray)
                .padding(12.dp),
            singleLine = true,
            label = { Text("Password") } // Подсказка для поля password
        )

        // Кнопка авторизации
        Button(
            onClick = {
                // Логика авторизации
                if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                    // Переход на главный экран
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true } // Убираем экран авторизации
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Text(text = "Log in")
        }

        // Кнопка "Создать аккаунт"
        Button(
            onClick = {
                // Логика для перехода на экран регистрации
                navController.navigate("register") // Экран регистрации
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text(text = "Create Account")
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}
