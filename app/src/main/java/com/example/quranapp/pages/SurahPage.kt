package com.example.quranapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranapp.R

@Preview
@Composable
fun SurahScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Al-Fatiah",
                        color = Color(R.color.main_color),
                        fontSize = 22.sp,
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
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = {
            SurahContent()
        }
    )
}

@Composable
fun SurahContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.Transparent,
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
                    )
                    Text(
                        modifier = Modifier.padding(top = 3.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(color = Color.Gray, fontSize = 16.sp)
                            ) {
                                append("Meccan")
                                append(" • ")
                                append("7")
                                append(if (true) " verses" else " verse")
                            }
                        }
                    )
                    Text(
                        text = "َﻦﻳِمَلٰعْلا ِّبَر ِهَّلِل ُدْمَحْلا",
                        color = Color(R.color.main_color),
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}

