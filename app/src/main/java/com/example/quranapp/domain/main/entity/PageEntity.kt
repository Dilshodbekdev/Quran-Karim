package com.example.quranapp.domain.main.entity

import com.example.quranapp.domain.juz.entity.Ayah

data class PageEntity(
    val number: Int,
    val ayahs: List<Ayah>
)