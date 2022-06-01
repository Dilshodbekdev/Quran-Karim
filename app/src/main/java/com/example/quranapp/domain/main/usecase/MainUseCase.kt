package com.example.quranapp.domain.main.usecase

import com.example.quranapp.domain.main.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(private val repository: MainRepository) {

    suspend fun surahs() = repository.getAllSurah()
}