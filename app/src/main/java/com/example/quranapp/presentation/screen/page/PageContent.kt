package com.example.quranapp.presentation.screen.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quranapp.R
import com.example.quranapp.presentation.navigation.Screen
import com.example.quranapp.utils.AppBarCollapsedHeight
import com.example.quranapp.utils.AppBarExpendedHeight
import com.example.quranapp.utils.fonts
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageContent(navController: NavController) {
    val list = (1..604).map { it.toString() }

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
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
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
            cells = GridCells.Fixed(3),

            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(list.size) { index ->
                    PageItem(index = index + 1, navController = navController)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PageItem(index: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp,
        onClick = {
            navController.navigate(
                route = Screen.PageDetails.passPageData(
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
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .align(Alignment.TopStart),
                text = "Page $index",
                color = Color.White,
                fontSize = 16.sp,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageContent(scrollState: LazyListState, navController: NavController) {
    val list = (1..604).map { it.toString() }

    /* LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
         item {
             Column(
                 modifier = Modifier.padding(16.dp)
             ) {
                 for (i in 0..24) {
                     Card(
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(bottom = 5.dp)
                             .height(75.dp),
                         elevation = 5.dp,
                         shape = RoundedCornerShape(10.dp)
                     ) {
                         Row(
                             verticalAlignment = Alignment.CenterVertically
                         ) {
                             Image(
                                 painter = painterResource(id = R.drawable.bg_card),
                                 contentDescription = "Profile Image",
                                 modifier = Modifier
                                     .size(60.dp)
                                     .clip(CircleShape)
                             )

                             Spacer(modifier = Modifier.width(10.dp))

                             Column {
                                 Text(
                                     text = "Developer",
                                     color = Color.Black,
                                     fontSize = 15.sp,
                                     fontWeight = FontWeight.Bold
                                 )
                                 Text(
                                     text = "+91-1234567890",
                                     color = Color.Black,
                                     fontSize = 12.sp
                                 )
                             }
                         }
                     }

                     Spacer(modifier = Modifier.height(10.dp))
                 }
             }
         }
     }*/

    LazyVerticalGrid(
        cells = GridCells.Fixed(3),

        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = AppBarExpendedHeight,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(list.size) { index ->
                PageItem(index = index + 1, navController = navController)
            }
        },
        state = scrollState
    )
}
