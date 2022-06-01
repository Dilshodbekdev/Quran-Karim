package com.example.quranapp.presentation.screen.juz

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quranapp.presentation.screen.juz_details.JuzViewModel

@Composable
fun JuzScreen(navController: NavController) {
    Scaffold(
        content = {
            JuzContent(navController = navController)
        }
    )
}