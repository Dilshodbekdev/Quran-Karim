package com.example.quranapp.presentation.screen.surah_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapp.data.ayah.remote.dto.AyahsResponse
import com.example.quranapp.data.common.utils.WrappedListResponse
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.main.remote.dto.SurahResponse
import com.example.quranapp.domain.ayah.entity.Ayah
import com.example.quranapp.domain.ayah.entity.AyahsEntity
import com.example.quranapp.domain.ayah.usecase.AyahUseCase
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.main.entity.SurahEntity
import com.example.quranapp.presentation.screen.main.MainViewModel
import com.example.quranapp.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AyahViewModel @Inject constructor(
    private val useCase: AyahUseCase,
    private val helper: NetworkHelper
) : ViewModel() {

    private val _ayahs = MutableStateFlow<AyahsState>(AyahsState.Init)
    val ayahs get() = _ayahs

    fun fetchAyahs(surah: Int) {
        if (helper.isNetworkConnected()) {
            viewModelScope.launch {
                useCase.ayahsBySurah(surah).onStart {
                    _ayahs.value = AyahsState.IsLoading(true)
                }.catch { e ->
                    _ayahs.value = AyahsState.IsLoading(false)
                    _ayahs.value = AyahsState.ShowToast(e.message.toString())
                }.collect { value ->
                    _ayahs.value = AyahsState.IsLoading(false)
                    when (value) {
                        is BaseResult.Success ->
                            _ayahs.value = AyahsState.Success(value.data.ayahs)
                        is BaseResult.Error ->
                            _ayahs.value = AyahsState.Error(value.rawResponse)
                    }
                }
            }
        }
    }

    sealed class AyahsState {
        object Init : AyahsState()
        data class IsLoading(val isLoading: Boolean) : AyahsState()
        data class ShowToast(val message: String) : AyahsState()
        data class Success(val entity: List<Ayah>) : AyahsState()
        data class Error(val rawResponse: WrappedResponse<AyahsResponse>) :
            AyahsState()
    }
}