package com.example.quranapp.domain.juz.usecase

import com.example.quranapp.domain.juz.JuzRepository
import javax.inject.Inject

class JuzUseCase @Inject constructor(private val repository: JuzRepository) {

    suspend fun juzDetails(juz: Int) = repository.getJuzByNumber(juz)
}