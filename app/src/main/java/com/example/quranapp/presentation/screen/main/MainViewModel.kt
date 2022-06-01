package com.example.quranapp.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapp.data.common.utils.WrappedListResponse
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.main.remote.dto.SurahResponse
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.main.entity.SurahEntity
import com.example.quranapp.domain.main.usecase.MainUseCase
import com.example.quranapp.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: MainUseCase,
    private val helper: NetworkHelper
) : ViewModel() {

    private val _surahs = MutableStateFlow<SurahsState>(SurahsState.Init)
    val surahs get() = _surahs

    init {
        fetchSurahs()
    }

    private fun fetchSurahs() {
        if (helper.isNetworkConnected()) {
            viewModelScope.launch {
                useCase.surahs().onStart {
                    _surahs.value = SurahsState.IsLoading(true)
                }.catch { e ->
                    _surahs.value = SurahsState.IsLoading(false)
                    _surahs.value = SurahsState.ShowToast(e.message.toString())
                }.collect { value ->
                    _surahs.value = SurahsState.IsLoading(false)
                    when (value) {
                        is BaseResult.Success ->
                            _surahs.value = SurahsState.Success(value.data)
                        is BaseResult.Error ->
                            _surahs.value = SurahsState.Error(value.rawResponse)
                    }
                }
            }
        }
    }

    sealed class SurahsState {
        object Init : SurahsState()
        data class IsLoading(val isLoading: Boolean) : SurahsState()
        data class ShowToast(val message: String) : SurahsState()
        data class Success(val entity: List<SurahEntity>) : SurahsState()
        data class Error(val rawResponse: WrappedListResponse<SurahResponse>) :
            SurahsState()
    }
}