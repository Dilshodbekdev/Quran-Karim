package com.example.quranapp.data.main.remote.dto

import androidx.annotation.Keep
import com.example.quranapp.data.juz.remote.dto.Ayah
import com.google.gson.annotations.SerializedName

@Keep
data class SajdaResponse(
    @SerializedName("ayahs")
    val ayahs: List<Ayah>
)

