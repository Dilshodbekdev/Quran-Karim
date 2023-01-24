
package com.example.quranapp.presentation.screen.quran

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quranapp.presentation.screen.main.MainViewModel

@ExperimentalAnimationApi
@Composable
fun QuranScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),

    ) {

    Surface(color = MaterialTheme.colors.background) {


    }

    /*Scaffold(
        content = {
            QuranContent(navController = navController, mainViewModel = mainViewModel)
        }
    )*/
}
