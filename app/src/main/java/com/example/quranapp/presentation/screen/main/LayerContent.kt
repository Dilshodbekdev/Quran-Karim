package com.example.quranapp.presentation.screen.main

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.util.lerp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quranapp.R
import com.example.quranapp.domain.main.entity.SurahEntity
import com.example.quranapp.presentation.components.VerticalNestedScrollView
import com.example.quranapp.presentation.components.rememberNestedScrollViewState
import com.example.quranapp.presentation.navigation.Screen
import com.example.quranapp.utils.fonts
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LayerContent(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val nestedScrollViewState = rememberNestedScrollViewState()

    val surahState = mainViewModel.surahs.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = "Quran+",
            fontSize = 26.sp,
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.text_name_color)
        )
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 120.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            count = 2,
            state = pagerState
        ) { page ->
            when (page) {
                0 -> {
                    Card(
                        modifier = Modifier
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                                lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                            .fillMaxWidth()
                            .height(120.dp)
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
                            Row(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_book),
                                    contentDescription = null,

                                    )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Last Read",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp),
                            ) {
                                Text(
                                    text = "Al-Faatiha",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                )
                                Text(
                                    text = "Ayah No: 1",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
                else -> {
                    Card(
                        modifier = Modifier
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                                lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(Color.Transparent)
                            .clickable {
                                navController.navigate(route = Screen.JuzScreen.route)
                            },
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
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 5.dp),
            activeColor = colorResource(id = R.color.main_color),
            inactiveColor = colorResource(R.color.item_color).copy(alpha = 0.35f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 5.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart),
                text = stringResource(R.string.surah_name),
                fontSize = 16.sp,
                color = colorResource(id = R.color.text_name_color)
            )
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd),
                text = stringResource(R.string.all),
                fontSize = 14.sp,
                color = colorResource(id = R.color.main_color)
            )
        }
        when (surahState) {
            is MainViewModel.SurahsState.IsLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is MainViewModel.SurahsState.ShowToast -> {
                Toast.makeText(context, surahState.message, Toast.LENGTH_SHORT).show()
            }

            is MainViewModel.SurahsState.Error -> {
                Toast.makeText(
                    context,
                    surahState.rawResponse.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            is MainViewModel.SurahsState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(items = surahState.entity) { surah ->
                        SurahItem(surah = surah, navController = navController)
                    }
                }
            }
            else -> {}
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart),
                text = "Ayah sajda",
                fontSize = 16.sp,
                color = colorResource(id = R.color.text_name_color)
            )
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd),
                text = stringResource(R.string.all),
                fontSize = 14.sp,
                color = colorResource(id = R.color.main_color)
            )
        }
    }
}

@Composable
fun SurahItem(surah: SurahEntity, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                navController.navigate(
                    route = Screen.SurahDetails.passSurahData(
                        number = surah.number,
                        name = surah.name,
                        englishName = surah.englishName,
                        englishNameTranslation = surah.englishNameTranslation,
                        numberOfAyahs = surah.numberOfAyahs,
                        revelationType = surah.revelationType
                    )
                )
            },
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(CircleShape)
            ) {
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "star",
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = surah.number.toString(),
                    color = colorResource(id = R.color.text_name_color),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier.padding(bottom = 3.dp),
                    text = surah.englishName,
                    fontSize = 16.sp,
                    color = colorResource(R.color.text_name_color),
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(id = R.color.unselected_tab_color),
                                fontSize = 12.sp,

                                )
                        ) {
                            append(surah.revelationType)
                            append(" • ")
                            append(surah.numberOfAyahs.toString())
                            append(if (surah.numberOfAyahs > 1) " ayahs" else " ayah")
                        }
                    }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = surah.name,
            fontSize = 20.sp,
            style = TextStyle(color = colorResource(id = R.color.arabic_text_color))
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(0.5.dp)
                .background(colorResource(R.color.item_color).copy(alpha = 0.35f))
        )
    }
}