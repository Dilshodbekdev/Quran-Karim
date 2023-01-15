package com.example.quranapp.domain.page.usecase

import com.example.quranapp.domain.page.PageRepository
import javax.inject.Inject

class PageUseCase @Inject constructor(private val repository: PageRepository) {

    suspend fun pageDetails(page: Int) = repository.getPageByNumber(page)
}