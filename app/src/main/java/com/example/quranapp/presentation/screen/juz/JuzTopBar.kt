package com.example.quranapp.presentation.screen.juz

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranapp.R
import com.example.quranapp.utils.fonts

@Composable
fun JuzTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Quran+",
                fontSize = 20.sp,
                color = colorResource(R.color.text_name_color),
                fontFamily = fonts,
                fontWeight = FontWeight.Bold
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = colorResource(id = R.color.unselected_tab_color)
                )
            }
        }
    )
}