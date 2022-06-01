package com.example.quranapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quranapp.presentation.screen.juz.JuzScreen
import com.example.quranapp.presentation.screen.juz_details.JuzDetailsScreen
import com.example.quranapp.presentation.screen.main.MainScreen
import com.example.quranapp.presentation.screen.quran.QuranScreen
import com.example.quranapp.presentation.screen.settings.SettingsScreen
import com.example.quranapp.presentation.screen.splash.SplashScreen
import com.example.quranapp.presentation.screen.surah_details.SurahScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.QuranScreen.route) {
            QuranScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen()
        }
        composable(route = Screen.JuzScreen.route) {
            JuzScreen(navController = navController)
        }
        composable(
            route = Screen.JuzDetails.route,
            arguments = listOf(
                navArgument("number") {
                    type = NavType.IntType
                }
            )
        ) {
            val number = it.arguments?.getInt("number")!!

            JuzDetailsScreen(number = number)
        }
        composable(
            route = Screen.SurahDetails.route,
            arguments = listOf(
                navArgument("number") {
                    type = NavType.IntType
                },
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("englishName") {
                    type = NavType.StringType
                },
                navArgument("englishNameTranslation") {
                    type = NavType.StringType
                },
                navArgument("numberOfAyahs") {
                    type = NavType.IntType
                },
                navArgument("revelationType") {
                    type = NavType.StringType
                },
            )
        ) {
            val number = it.arguments?.getInt("number")!!
            val name = it.arguments?.getString("name")!!
            val englishName = it.arguments?.getString("englishName")!!
            val englishNameTranslation = it.arguments?.getString("englishNameTranslation")!!
            val numberOfAyahs = it.arguments?.getInt("numberOfAyahs")!!
            val revelationType = it.arguments?.getString("revelationType")!!

            SurahScreen(
                navController = navController,
                number = number,
                name = name,
                englishName = englishName,
                englishNameTranslation = englishNameTranslation,
                numberOfAyahs = numberOfAyahs,
                revelationType = revelationType
            )
        }
    }
}
