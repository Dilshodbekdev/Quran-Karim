package com.example.quranapp.presentation.screen.juz_details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun JuzDetailsScreen(
    navController: NavController,
    juzViewModel: JuzViewModel = hiltViewModel(),
    number: Int
) {
    LaunchedEffect(Unit) {
        juzViewModel.fetchAyahs(number)
    }

    Scaffold(
        topBar = { JuzDetailsTopBar() },
        content = { JuzDetailsContent(juzViewModel,navController) }
    )
}