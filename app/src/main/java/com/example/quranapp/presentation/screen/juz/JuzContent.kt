@file:OptIn(ExperimentalMaterialApi::class)

package com.example.quranapp.presentation.screen.juz

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.quranapp.R
import com.example.quranapp.presentation.navigation.Screen
import com.example.quranapp.utils.fonts
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JuzContent(navController: NavController) {
    val list = (1..30).map { it.toString() }

    Column(modifier = Modifier) {
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier,
                    text = "Quran+",
                    fontSize = 14.sp,
                    fontFamily = fonts,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.text_name_color)
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Khatam Quran",
                fontSize = 20.sp,
                fontFamily = fonts,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.text_name_color)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 16.dp)
                .background(Color.Transparent),
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(id = R.color.brush_1),
                                colorResource(id = R.color.brush_2)
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_card1),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Play My Own",
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Khatam Quran",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),

            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(list.size) { index ->
                    JuzItem(index = index + 1, navController = navController)
                }
            }
        )
    }
}

@Composable
fun JuzItem(index: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp,
        onClick = {
            navController.navigate(
                route = Screen.JuzDetails.passJuzData(
                    number = index
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.brush_1),
                            colorResource(id = R.color.brush_2)
                        )
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_quran),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(48.dp)
                    .padding(top = 16.dp, end = 16.dp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .align(Alignment.TopStart),
                text = "Juz $index",
                color = Color.White,
                fontSize = 18.sp,
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                text = "Start Reciting",
                color = Color.White,
                fontSize = 14.sp,
            )

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(20.dp)),
                progress = 0f,
                backgroundColor = colorResource(id = R.color.item_color),
                color = colorResource(id = R.color.text_name_color)
            )
        }
    }
}