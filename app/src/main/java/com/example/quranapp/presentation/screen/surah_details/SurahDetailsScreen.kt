package com.example.quranapp.presentation.screen.surah_details

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SurahScreen(
    navController: NavHostController = rememberNavController(),
    number: Int = 0,
    name: String = "",
    englishName: String = "",
    englishNameTranslation: String,
    numberOfAyahs: Int,
    revelationType: String = ""
) {

    val ayahViewModel: AyahViewModel = hiltViewModel()
    //rememberSystemUiController().isStatusBarVisible = false
    LaunchedEffect(Unit) {
        ayahViewModel.fetchAyahs(number)
    }

    Scaffold(
        topBar = {
            SurahDetailsTopBar(englishName, navController)
        },
        content = {
            SurahContent(
                ayahViewModel = ayahViewModel,
                number = number,
                name = name,
                englishName = englishName,
                englishNameTranslation = englishNameTranslation,
                numberOfAyahs = numberOfAyahs,
                revelationType = revelationType
            )
        }
    )
}
