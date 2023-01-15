package com.example.quranapp.presentation.screen.page_details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quranapp.presentation.screen.juz_details.JuzDetailsContent
import com.example.quranapp.presentation.screen.juz_details.JuzDetailsTopBar
import com.example.quranapp.presentation.screen.juz_details.JuzViewModel
import com.example.quranapp.presentation.screen.page.PageContent

@Composable
fun PageDetailsScreen(
    navController: NavController,
    pageViewModel: PageViewModel = hiltViewModel(),
    number: Int
) {
    LaunchedEffect(Unit) {
        pageViewModel.fetchAyahs(number)
    }

    Scaffold(
        content = { PageDetailsContent(pageViewModel, navController) }
    )
}