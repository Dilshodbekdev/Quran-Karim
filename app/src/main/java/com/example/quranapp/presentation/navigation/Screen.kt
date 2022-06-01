package com.example.quranapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import com.example.quranapp.R

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object MainScreen : Screen("main_screen")
    object QuranScreen : Screen("quran_screen")
    object SettingsScreen : Screen("settings_screen")
    object JuzScreen : Screen("juz_screen")
    object JuzDetails : Screen("juz_details/{number}") {
        fun passJuzData(
            number: Int
        ) = "juz_details/$number"
    }

    object SurahDetails :
        Screen("surah_details/{number}/{name}/{englishName}/{englishNameTranslation}/{numberOfAyahs}/{revelationType}") {
        fun passSurahData(
            number: Int,
            name: String,
            englishName: String,
            englishNameTranslation: String,
            numberOfAyahs: Int,
            revelationType: String
        ) =
            "surah_details/$number/$name/$englishName/$englishNameTranslation/$numberOfAyahs/$revelationType"
    }
}

val bottomNavigationItems = listOf(
    BottomModule(Screen.QuranScreen.route, "Quran", R.drawable.ic_main),/*
    BottomModule(Screen.QuranScreen.route, "Quran", Icons.Filled.Book),*/
    BottomModule(Screen.SettingsScreen.route, "Settings", R.drawable.ic_settings)
)

data class BottomModule(
    val screen: String,
    val title: String,
    val painter: Int
)