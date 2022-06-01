package com.example.quranapp.domain.ayah.usecase

import com.example.quranapp.domain.ayah.AyahRepository
import javax.inject.Inject

class AyahUseCase @Inject constructor(private val repository: AyahRepository) {

    suspend fun ayahsBySurah(surah: Int) = repository.ayahs(surah)

    suspend fun ayah(reference: Int) = repository.ayah(reference)
}