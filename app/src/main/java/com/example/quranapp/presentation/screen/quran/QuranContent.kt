package com.example.quranapp.presentation.screen.quran

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quranapp.R
import com.example.quranapp.domain.main.entity.SurahEntity
import com.example.quranapp.presentation.navigation.Screen
import com.example.quranapp.presentation.screen.main.MainViewModel
import com.example.quranapp.utils.fonts

@Composable
fun QuranContent(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = "Al-Qur'an",
            fontSize = 26.sp,
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.text_name_color)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(0.5.dp)
                .background(colorResource(R.color.item_color).copy(alpha = 0.35f))
        )

        when (val surahState = mainViewModel.surahs.collectAsState().value) {
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
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
        ) {
            Text(
                text = surah.name,
                fontSize = 20.sp,
                style = TextStyle(color = colorResource(id = R.color.arabic_text_color))
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = null,
                tint = colorResource(id = R.color.item_color)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(0.5.dp)
                .background(colorResource(R.color.item_color).copy(alpha = 0.35f))
        )
    }
}