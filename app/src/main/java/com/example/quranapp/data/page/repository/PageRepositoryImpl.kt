package com.example.quranapp.data.page.repository

import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.page.remote.api.PageApi
import com.example.quranapp.data.page.remote.dto.PageResponse
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.juz.entity.Ayah
import com.example.quranapp.domain.main.entity.SurahEntity
import com.example.quranapp.domain.page.PageRepository
import com.example.quranapp.domain.page.entity.PageEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PageRepositoryImpl @Inject constructor(private val api: PageApi):PageRepository {
    override suspend fun getPageByNumber(page: Int): Flow<BaseResult<PageEntity, WrappedResponse<PageResponse>>> {
        return flow {
            val page = api.getPageByNumber(page)
            if (page.isSuccessful && page.body() != null) {
                val response: PageEntity
                val data = page.body()!!.data
                val ayahsData = mutableListOf<Ayah>()
                data?.ayahs?.forEach { item ->
                    val surah = SurahEntity(
                        number = item.surah?.number ?: 0,
                        name = item.surah?.name ?: "",
                        englishName = item.surah?.englishName ?: "",
                        englishNameTranslation = item.surah?.englishNameTranslation ?: "",
                        revelationType = item.surah?.revelationType ?: "",
                        numberOfAyahs = item.surah?.numberOfAyahs ?: 0
                    )

                    ayahsData.add(
                        Ayah(
                            number = item.number ?: 0,
                            text = item.text ?: "",
                            surah = surah,
                            numberInSurah = item.numberInSurah ?: 0,
                            juz = item.juz ?: 0,
                            manzil = item.manzil ?: 0,
                            page = item.page ?: 0,
                            ruku = item.ruku ?: 0,
                            hizbQuarter = item.hizbQuarter ?: 0
                        )
                    )
                }
                response = PageEntity(number = data?.number ?: 0, ayahs = ayahsData)
                emit(BaseResult.Success(response))
            } else {
                val type = object : TypeToken<WrappedResponse<PageResponse>>() {}.type
                val err = Gson().fromJson<WrappedResponse<PageResponse>>(
                    page.errorBody()!!.charStream(), type
                )!!
                err.code = page.code()
                emit(BaseResult.Error(err))
            }
        }
    }
}