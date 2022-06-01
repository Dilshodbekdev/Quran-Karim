package com.example.quranapp.presentation.screen.surah_details

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quranapp.R
import com.example.quranapp.utils.fonts

@Composable
fun SurahDetailsTopBar(englishName: String = "", navController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = englishName,
                color = colorResource(R.color.title_text_color),
                fontSize = 20.sp,
                fontFamily = fonts,
                fontWeight = FontWeight.Bold
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = colorResource(id = R.color.unselected_tab_color)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = colorResource(id = R.color.unselected_tab_color)
                )
            }
        }
    )
}
