package com.example.quranapp.data.main.repository

import com.example.quranapp.data.common.utils.WrappedListResponse
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.main.remote.api.MainApi
import com.example.quranapp.data.main.remote.dto.*
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.main.MainRepository
import com.example.quranapp.domain.main.entity.*
import com.example.quranapp.domain.juz.entity.Ayah
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val api: MainApi) : MainRepository {

    override suspend fun getAllSurah(): Flow<BaseResult<List<SurahEntity>, WrappedListResponse<SurahResponse>>> {
        return flow {
            val surahs = api.getAllSurah()
            if (surahs.isSuccessful && surahs.body() != null) {
                val body = surahs.body()
                val response = mutableListOf<SurahEntity>()
                body?.data?.forEach { item ->
                    response.add(
                        SurahEntity(
                            number = item.number ?: 0,
                            name = item.name ?: "",
                            englishName = item.englishName ?: "",
                            englishNameTranslation = item.englishNameTranslation ?: "",
                            numberOfAyahs = item.numberOfAyahs ?: 0,
                            revelationType = item.revelationType ?: ""
                        )
                    )
                }
                emit(BaseResult.Success(response))
            } else {
                val type = object : TypeToken<WrappedListResponse<SurahResponse>>() {}.type
                val err = Gson().fromJson<WrappedListResponse<SurahResponse>>(
                    surahs.errorBody()!!.charStream(), type
                )!!
                err.code = surahs.code()
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun search(keyword: String): Flow<BaseResult<SearchEntity, WrappedResponse<SearchResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun pageByNumber(number: Int): Flow<BaseResult<PageEntity, WrappedResponse<PageResponse>>> {
        return flow {
            val page = api.pageByNumber(number)
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

    override suspend fun sajda(): Flow<BaseResult<SajdaEntity, WrappedResponse<SajdaResponse>>> {
        return flow {
            val sajda = api.sajda()
            if (sajda.isSuccessful && sajda.body() != null) {
                val response: SajdaEntity
                val data = sajda.body()!!.data
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
                response = SajdaEntity(ayahs = ayahsData)
                emit(BaseResult.Success(response))
            } else {
                val type = object : TypeToken<WrappedResponse<SajdaResponse>>() {}.type
                val err = Gson().fromJson<WrappedResponse<SajdaResponse>>(
                    sajda.errorBody()!!.charStream(), type
                )!!
                err.code = sajda.code()
                emit(BaseResult.Error(err))
            }
        }
    }
}