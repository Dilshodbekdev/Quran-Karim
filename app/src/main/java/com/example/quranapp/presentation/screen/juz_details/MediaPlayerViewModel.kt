package com.example.quranapp.presentation.screen.juz_details

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.quranapp.domain.juz.entity.Ayah
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JuzMediaPlayerViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel(),
    MediaPlayer.OnCompletionListener,
    MediaPlayer.OnPreparedListener {

    private var mediaPlayer = MediaPlayer()

    val isLoadingMusicId = MutableStateFlow<Int?>(null)

    val isPlaying = MutableStateFlow<Int?>(null)

    val currentAyah = MutableStateFlow<Ayah?>(null)

    fun setMusicData(ayah: Ayah) {
        if (currentAyah.value?.number == ayah.number) {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPlaying.value = null
            } else {
                mediaPlayer.start()
                isPlaying.value = ayah.number
            }
            return
        }
        isLoadingMusicId.value = ayah.number
        currentAyah.value = ayah
        val uri: Uri =
            Uri.parse("https://cdn.islamic.network/quran/audio/128/ar.alafasy/${ayah.number}.mp3")
        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(context, uri)
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
                isLoadingMusicId.value = null
                isPlaying.value = ayah.number
            }
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mediaPlayer.setOnCompletionListener {
            currentAyah.value = null
            isPlaying.value = null
        }
    }

    private var currentDuration: CountDownTimer? = null
    private val _currentMinutes = MutableStateFlow(0)

    private val _audioFinish = MutableStateFlow(false)
    val audioFinish: StateFlow<Boolean> get() = _audioFinish

    fun getMediaDuration(mediaPlayer: MediaPlayer) {
        currentDuration = object : CountDownTimer(mediaPlayer.duration.toLong(), 500) {
            override fun onTick(milliSec: Long) {
                _currentMinutes.value = mediaPlayer.currentPosition
            }

            override fun onFinish() {
                _audioFinish.value = true
                Log.d("TAG", "onFinish: Media Player Finished")
            }
        }
        currentDuration!!.start()
    }

    fun getFirstColor(): Color {
        val random = Random()
        val color: Int = android.graphics.Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )
        return Color(color)
    }

    fun getSecondColor(): Color {
        val random = Random()
        val color: Int = android.graphics.Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )
        return Color(color)
    }


    override fun onCompletion(mp: MediaPlayer?) {

    }

    override fun onPrepared(mp: MediaPlayer?) {

    }


}