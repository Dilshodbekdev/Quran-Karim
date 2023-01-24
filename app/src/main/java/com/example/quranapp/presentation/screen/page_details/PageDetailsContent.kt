package com.example.quranapp.presentation.screen.page_details

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.quranapp.R
import com.example.quranapp.domain.juz.entity.Ayah
import com.example.quranapp.presentation.screen.juz_details.JuzMediaPlayerViewModel
import com.example.quranapp.utils.fonts

@Composable
fun PageDetailsContent(pageViewModel: PageViewModel, navController: NavController) {

    val context = LocalContext.current

    when (val state = pageViewModel.pageDetails.collectAsState().value) {
        is PageViewModel.PageState.IsLoading ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        is PageViewModel.PageState.ShowToast -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            Log.d("TAG", "AyahList: ${state.message}")
        }
        is PageViewModel.PageState.Error -> {
            Toast.makeText(context, state.rawResponse.toString(), Toast.LENGTH_SHORT).show()
            Log.d("TAG", "AyahList: ${state.rawResponse}")
        }
        is PageViewModel.PageState.Success -> {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(items = state.entity) { index, item ->
                    PageDetailItem(ayah = item, index = index + 1)
                }
            }
        }
        else -> {}
    }
}

@Composable
fun PageDetailItem(
    ayah: Ayah,
    index: Int,
    mediaPlayerViewModel: JuzMediaPlayerViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val isLoadingMusic = mediaPlayerViewModel.isLoadingMusicId.collectAsState().value
    val currentAyah = mediaPlayerViewModel.currentAyah.collectAsState().value
    val isPlaying = mediaPlayerViewModel.isPlaying.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
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
                        text = index.toString(),
                        color = colorResource(id = R.color.text_name_color),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(id = R.color.unselected_tab_color),
                                fontSize = 14.sp
                            )
                        ) {
                            append(ayah.surah.englishName)
                            append(" • ")
                            append(ayah.numberInSurah.toString() + " ayah")
                        }
                    }
                )
            }

            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.CenterEnd)
                    .background(colorResource(R.color.arabic_text_color).copy(alpha = 0.05f)),
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
                    tint = colorResource(id = R.color.arabic_text_color),
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data("https://cdn.islamic.network/quran/images/${ayah.surah.number}_${ayah.numberInSurah}.png")
                    .size(Size.ORIGINAL)
                    .placeholder(R.drawable.bg_card1)
                    .error(R.drawable.bg_card)
                    .build(),
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = ayah.text,
            fontSize = 18.sp,
            style = TextStyle(color = colorResource(id = R.color.text_name_color)),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Tafsir:",
            color = colorResource(id = R.color.arabic_text_color),
            fontSize = 16.sp,
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = Modifier
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