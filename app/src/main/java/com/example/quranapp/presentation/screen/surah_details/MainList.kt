package com.example.quranapp.presentation.screen.surah_details

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.quranapp.R
import com.example.quranapp.utils.AppBarExpendedHeight1
import com.example.quranapp.utils.fonts
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException


lateinit var mediaPlayer: MediaPlayer

@Composable
fun AyahList(
    modifier: Modifier = Modifier,
    ayahViewModel: AyahViewModel,
    number: Int,
    scrollState: LazyListState,
    mediaPlayerViewModel: MediaPlayerViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        mediaPlayer = MediaPlayer()
    }

    val context = LocalContext.current
    val isLoadingMusic = mediaPlayerViewModel.isLoadingMusicId.collectAsState().value
    val currentAyah = mediaPlayerViewModel.currentAyah.collectAsState().value
    val isPlaying = mediaPlayerViewModel.isPlaying.collectAsState().value

    when (val state = ayahViewModel.ayahs.collectAsState().value) {
        is AyahViewModel.AyahsState.IsLoading ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        is AyahViewModel.AyahsState.ShowToast -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            Log.d("TAG", "AyahList: ${state.message}")
        }
        is AyahViewModel.AyahsState.Error -> {
            Toast.makeText(context, state.rawResponse.toString(), Toast.LENGTH_SHORT).show()
            Log.d("TAG", "AyahList: ${state.rawResponse}")
        }
        is AyahViewModel.AyahsState.Success -> {
            val list = state.entity

            LazyColumn(
                contentPadding = PaddingValues(top = AppBarExpendedHeight1),
                modifier = modifier
                    .fillMaxSize(),
                state = scrollState
            ) {
                items(state.entity) { ayah ->

                    Column(
                        modifier = modifier
                            .padding(vertical = 10.dp, horizontal = 16.dp)
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(colorResource(R.color.arabic_text_color).copy(alpha = 0.05f))
                                .padding(start = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(colorResource(R.color.arabic_text_color))
                                    .align(Alignment.CenterStart),
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = ayah.numberInSurah.toString(),
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontSize = 14.sp
                                )
                            }

                            Row(
                                modifier = modifier
                                    .align(Alignment.CenterEnd)
                            ) {
                                IconButton(
                                    modifier = modifier,
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_share),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.arabic_text_color)
                                    )
                                }
                                IconButton(
                                    modifier = modifier,
                                    onClick = {
                                        mediaPlayerViewModel.setMusicData(ayah)
                                    },
                                    enabled = isLoadingMusic != ayah.number
                                ) {
                                    Icon(
                                        painter = if (isLoadingMusic != ayah.number) {
                                            if (isPlaying == null) {
                                                painterResource(
                                                    id = R.drawable.ic_play
                                                )
                                            } else if (currentAyah?.number == ayah.number) {
                                                painterResource(id = R.drawable.ic_pause)
                                            } else {
                                                painterResource(id = R.drawable.ic_play)
                                            }
                                        } else {
                                            painterResource(
                                                id = R.drawable.ic_downloading
                                            )
                                        },
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.arabic_text_color)
                                    )
                                }

                                IconButton(
                                    modifier = modifier,
                                    onClick = {

                                    }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_bookmark),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.arabic_text_color)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = modifier.height(10.dp))
                        Image(
                            modifier = modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(context)
                                    .data("https://cdn.islamic.network/quran/images/${number}_${ayah.numberInSurah}.png")
                                    .size(Size.ORIGINAL)
                                    .placeholder(R.drawable.bg_card1)
                                    .error(R.drawable.bg_card)
                                    .build(),
                            ),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            modifier = modifier
                                .fillMaxWidth(),
                            text = ayah.text,
                            color = colorResource(id = R.color.text_name_color),
                            fontSize = 16.sp,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = modifier.height(16.dp))
                        Text(
                            modifier = modifier
                                .fillMaxWidth(),
                            text = "Tafsir:",
                            color = colorResource(id = R.color.arabic_text_color),
                            fontSize = 16.sp,
                            fontFamily = fonts,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            modifier = modifier
                                .fillMaxWidth(),
                            text = ayah.text.substring(
                                ayah.text.lastIndexOf('(') + 1,
                                ayah.text.lastIndexOf(')')
                            ),
                            color = colorResource(id = R.color.text_name_color),
                            fontSize = 16.sp,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .background(
                                    color = colorResource(R.color.item_color)
                                        .copy(alpha = 0.35f)
                                )
                        )
                    }
                }
            }
        }
        else -> {}
    }
}