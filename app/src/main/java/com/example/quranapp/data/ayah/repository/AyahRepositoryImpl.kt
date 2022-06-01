package com.example.quranapp.data.ayah.repository

import com.example.quranapp.data.ayah.remote.api.AyahApi
import com.example.quranapp.data.ayah.remote.dto.AyahResponse
import com.example.quranapp.data.ayah.remote.dto.AyahsResponse
import com.example.quranapp.data.common.utils.WrappedListResponse
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.domain.ayah.AyahRepository
import com.example.quranapp.domain.ayah.entity.Ayah
import com.example.quranapp.domain.ayah.entity.AyahEntity
import com.example.quranapp.domain.ayah.entity.AyahsEntity
import com.example.quranapp.domain.ayah.entity.EditionEntity
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.main.entity.SurahEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AyahRepositoryImpl @Inject constructor(private val api: AyahApi) : AyahRepository {
    override suspend fun ayah(reference: Int): Flow<BaseResult<AyahEntity, WrappedResponse<AyahResponse>>> {
        return flow {
            val ayah = api.getAyah(reference)
            val response: AyahEntity
            if (ayah.isSuccessful && ayah.body() != null) {
                val data = ayah.body()?.data
                val editionData = ayah.body()?.data?.edition
                val surahData = ayah.body()?.data?.surah

                val edition = EditionEntity(
                    identifier = editionData?.identifier ?: "",
                    language = editionData?.language ?: "",
                    name = editionData?.name ?: "",
                    englishName = editionData?.englishName ?: "",
                    format = editionData?.format ?: "",
                    type = editionData?.type ?: "",
                    direction = editionData?.direction ?: ""
                )

                val surah = SurahEntity(
                    number = surahData?.number ?: 0,
                    name = surahData?.name ?: "",
                    englishName = surahData?.englishName ?: "",
                    englishNameTranslation = surahData?.englishNameTranslation ?: "",
                    numberOfAyahs = surahData?.numberOfAyahs ?: 0,
                    revelationType = surahData?.revelationType ?: ""
                )

                response = AyahEntity(
                    number = data?.number ?: 0,
                    audio = data?.audio ?: "",
                    audioSecondary = data?.audioSecondary ?: emptyList(),
                    text = data?.text ?: "",
                    edition = edition,
                    surah = surah,
                    numberInSurah = data?.numberInSurah ?: 0,
                    juz = data?.juz ?: 0,
                    manzil = data?.manzil ?: 0,
                    page = data?.page ?: 0,
                    ruku = data?.ruku ?: 0,
                    hizbQuarter = data?.hizbQuarter ?: 0
                )
                emit(BaseResult.Success(response))
            } else {
                val type = object : TypeToken<WrappedResponse<AyahResponse>>() {}.type
                val err = Gson().fromJson<WrappedResponse<AyahResponse>>(
                    ayah.errorBody()!!.charStream(), type
                )!!
                err.code = ayah.code()
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun ayahs(surah: Int): Flow<BaseResult<AyahsEntity, WrappedResponse<AyahsResponse>>> {
        return flow {
            val ayahs = api.getAyahsBySurah(surah)
            if (ayahs.isSuccessful && ayahs.body() != null) {
                val response: AyahsEntity
                val data = ayahs.body()?.data
                val editionData = ayahs.body()?.data?.edition
                val ayahsData = mutableListOf<Ayah>()

                ayahs.body()?.data?.ayahs?.forEach { item ->
                    ayahsData.add(
                        Ayah(
                            number = item.number ?: 0,
                            text = item.text ?: "",
                            numberInSurah = item.numberInSurah ?: 0,
                            juz = item.juz ?: 0,
                            manzil = item.manzil ?: 0,
                            page = item.page ?: 0,
                            ruku = item.ruku ?: 0,
                            hizbQuarter = item.hizbQuarter ?: 0
                        )
                    )
                }

                val edition = EditionEntity(
                    identifier = editionData?.identifier ?: "",
                    language = editionData?.language ?: "",
                    name = editionData?.name ?: "",
                    englishName = editionData?.englishName ?: "",
                    format = editionData?.format ?: "",
                    type = editionData?.type ?: "",
                    direction = editionData?.direction ?: ""
                )

                response = AyahsEntity(
                    number = data?.number ?: 0,
                    name = data?.name ?: "",
                    englishName = data?.englishName ?: "",
                    englishNameTranslation = data?.englishNameTranslation ?: "",
                    revelationType = data?.revelationType ?: "",
                    numberOfAyahs = data?.numberOfAyahs ?: 0,
                    ayahs = ayahsData,
                    edition = edition
                )

                emit(BaseResult.Success(response))
            } else {
                val type = object : TypeToken<WrappedResponse<AyahsResponse>>() {}.type
                val err = Gson().fromJson<WrappedResponse<AyahsResponse>>(
                    ayahs.errorBody()!!.charStream(), type
                )!!
                err.code = ayahs.code()
                emit(BaseResult.Error(err))
            }
        }
    }
}