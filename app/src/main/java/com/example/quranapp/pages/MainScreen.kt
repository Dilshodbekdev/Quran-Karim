package com.example.quranapp.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quranapp.R
import com.example.quranapp.models.SurahData
import com.example.quranapp.utils.showToast

@Preview
@Composable
fun MyToolbar(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Quran App",
                        fontSize = 22.sp,
                        fontStyle = FontStyle.Normal,
                        color = Color(R.color.main_color)
                    )
                },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 0.dp,
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = {
            MainPage(navController = navController)
        },
    )
}

@Composable
fun MainPage(navController: NavController) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Asslamualaikum",
            fontSize = 18.sp,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Tanvir Ahassan",
            color = Color.Black,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.Transparent)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = 0.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFD18CFB),
                                Color(0xFF9358FB)
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
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_book),
                        contentDescription = null,

                        )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Last Read",
                        color = Color.White,

                        )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp),
                ) {
                    Text(
                        text = "Al-Fatiah",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                    )
                    Text(
                        text = "Ayah No: 1",
                        color = Color.White,
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .padding(horizontal = 16.dp)
        )
        PostTabView(
            tabTexts = listOf(
                "Surah", "Para", "Page", "Hijb"
            )
        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> {
                TabItemSection(
                    surahs = listOf(
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7),
                        SurahData(1, "Al-Fatiah", "meccan", 7)
                    ), navController = navController
                )
            }
            else -> {
                TabItemSection(
                    surahs = listOf(
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7),
                        SurahData(1, "Al-Baqarah", "medinian", 7)
                    ), navController = navController
                )
            }
        }
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    tabTexts: List<String>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val activeColor = Color(R.color.main_color)
    val inActiveColor = Color.Gray
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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TabItemSection(
    surahs: List<SurahData>,
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
                        navController.navigate("surah_screen")
                    },
            ) {
                Row(
                    modifier = modifier
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(Color(R.color.main_color)),
                        ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = surah.id.toString(),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                    }
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            modifier = Modifier.padding(bottom = 3.dp),
                            text = surah.name,
                            fontSize = 20.sp,
                            color = Color.Black,
                        )
                        Text(
                            modifier = Modifier.padding(top = 3.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(color = Color.Gray, fontSize = 16.sp)
                                ) {
                                    append(surah.title)
                                    append(" • ")
                                    append(surah.verse.toString())
                                    append(if (surah.verse > 1) " verses" else " verse")
                                }
                            }
                        )
                    }
                }
                Text(
                    modifier = modifier.align(Alignment.CenterEnd),
                    text = "َﻦﻳِمَلٰعْلا ِّبَر ِهَّلِل ُدْمَحْلا",
                    color = Color(R.color.main_color),
                    fontSize = 24.sp
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(0.5.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}
