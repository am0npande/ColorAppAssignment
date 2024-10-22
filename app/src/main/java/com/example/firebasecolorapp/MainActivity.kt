package com.example.firebasecolorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.firebasecolorapp.presentation.screens.MainScreen
import com.example.firebasecolorapp.ui.theme.FirebaseColorAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseColorAppTheme {
                MainScreen()
            }
        }
    }
}


