package com.example.quranapp.presentation.screen.surah_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranapp.R
import okhttp3.internal.wait

@Composable
fun SurahContent(
    modifier: Modifier = Modifier,
    ayahViewModel: AyahViewModel,
    number: Int,
    name: String,
    englishName: String,
    englishNameTranslation: String,
    numberOfAyahs: Int,
    revelationType: String
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            shape = RoundedCornerShape(20.dp),
            elevation = 4.dp,
            backgroundColor = Color.Transparent
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
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                        .align(Alignment.BottomEnd)
                        .alpha(0.1f),
                    painter = painterResource(id = R.drawable.bg_card1),
                    contentDescription = null,
                )
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = englishName,
                        color = Color.White,
                        fontSize = 26.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = englishNameTranslation,
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(0.5.dp)
                            .background(Color.White)
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(top = 3.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(color = Color.White, fontSize = 14.sp)
                            ) {
                                append(revelationType)
                                append(" • ")
                                append("$numberOfAyahs")
                                append(if (numberOfAyahs > 1) " verses" else " verse")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = name,
                        color = Color.White,
                        fontSize = 38.sp
                    )
                }
            }
        }
        AyahList(ayahViewModel = ayahViewModel, number = number)
    }
}

@Composable
fun BackLayer(offset: Float, halHeightPx: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .padding(top = 16.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        elevation = 4.dp
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
                .alpha(offset / halHeightPx)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .align(Alignment.BottomEnd)
                    .alpha(0.1f),
                painter = painterResource(id = R.drawable.bg_card1),
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Al-Fatiah",
                    color = Color.White,
                    fontSize = 30.sp,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "The Opening",
                    color = Color.White,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(Color.White)
                        .padding(horizontal = 16.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(color = Color.White, fontSize = 16.sp)
                        ) {
                            append("Meccan")
                            append(" • ")
                            append("7")
                            append(if (true) " verses" else " verse")
                        }
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "َﻦﻳِمَلٰعْلا ِّبَر ِهَّلِل ُدْمَحْلا",
                    color = Color.White,
                    fontSize = 28.sp
                )
            }
        }
    }
}

/*
@Composable
fun FrontLayer() {
    AyahList()
}
*/
