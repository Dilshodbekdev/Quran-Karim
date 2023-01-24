package com.example.quranapp.domain.page.entity

import com.example.quranapp.domain.juz.entity.Ayah

data class PageEntity(
    val number: Int,
    val ayahs: List<Ayah>
)