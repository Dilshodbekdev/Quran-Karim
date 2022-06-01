package com.example.quranapp.data.juz.repository

import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.juz.remote.api.JuzApi
import com.example.quranapp.data.juz.remote.dto.JuzResponse
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.juz.JuzRepository
import com.example.quranapp.domain.juz.entity.Ayah
import com.example.quranapp.domain.juz.entity.JuzEntity
import com.example.quranapp.domain.main.entity.SurahEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JuzRepositoryImpl @Inject constructor(private val api: JuzApi) : JuzRepository {
    override suspend fun getJuzByNumber(juz: Int): Flow<BaseResult<JuzEntity, WrappedResponse<JuzResponse>>> {
        return flow {
            val juz = api.getJuzByNumber(juz)
            if (juz.isSuccessful && juz.body() != null) {
                val response: JuzEntity
                val data = juz.body()!!.data
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
                response = JuzEntity(number = data?.number ?: 0, ayahs = ayahsData)
                emit(BaseResult.Success(response))
            } else {
                val type = object : TypeToken<WrappedResponse<JuzResponse>>() {}.type
                val err = Gson().fromJson<WrappedResponse<JuzResponse>>(
                    juz.errorBody()!!.charStream(), type
                )!!
                err.code = juz.code()
                emit(BaseResult.Error(err))
            }
        }
    }
}