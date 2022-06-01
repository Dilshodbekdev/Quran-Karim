package com.example.quranapp.presentation.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quranapp.R
import com.example.quranapp.domain.main.entity.SurahEntity
import com.example.quranapp.presentation.navigation.Screen

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    tabTexts: List<String> = emptyList(),
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val activeColor = colorResource(id = R.color.selected_tab_color)
    val inActiveColor = colorResource(id = R.color.unselected_tab_color)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = activeColor,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        tabTexts.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = activeColor,
                unselectedContentColor = inActiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = item,
                    color = if (selectedTabIndex == index) activeColor else inActiveColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun TabItemSection(
    surahs: List<SurahEntity>,
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(surahs) { surah ->
            Box(
                modifier = modifier
                    .fillMaxWidth()
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
                    modifier = modifier
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
                    modifier = modifier.align(Alignment.CenterEnd),
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
    }
}
