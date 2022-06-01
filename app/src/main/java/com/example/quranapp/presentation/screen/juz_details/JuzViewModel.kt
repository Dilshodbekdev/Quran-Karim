package com.example.quranapp.presentation.screen.juz_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapp.data.ayah.remote.dto.AyahsResponse
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.juz.remote.dto.JuzResponse
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.juz.entity.Ayah
import com.example.quranapp.domain.juz.usecase.JuzUseCase
import com.example.quranapp.presentation.screen.surah_details.AyahViewModel
import com.example.quranapp.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JuzViewModel @Inject constructor(
    private val useCase: JuzUseCase,
    private val helper: NetworkHelper
) : ViewModel() {

    private val _juzDetails =
        MutableStateFlow<JuzState>(JuzState.Init)
    val juzDetails get() = _juzDetails

    fun fetchAyahs(juz: Int) {
        if (helper.isNetworkConnected()) {
            viewModelScope.launch {
                useCase.juzDetails(juz).onStart {
                    _juzDetails.value = JuzState.IsLoading(true)
                }.catch { e ->
                    _juzDetails.value = JuzState.IsLoading(false)
                    _juzDetails.value = JuzState.ShowToast(e.message.toString())
                }.collect { value ->
                    _juzDetails.value = JuzState.IsLoading(false)
                    when (value) {
                        is BaseResult.Success ->
                            _juzDetails.value = JuzState.Success(value.data.ayahs)
                        is BaseResult.Error ->
                            _juzDetails.value = JuzState.Error(value.rawResponse)
                    }
                }
            }
        }
    }

    sealed class JuzState {
        object Init : JuzState()
        data class IsLoading(val isLoading: Boolean) : JuzState()
        data class ShowToast(val message: String) : JuzState()
        data class Success(val entity: List<Ayah>) : JuzState()
        data class Error(val rawResponse: WrappedResponse<JuzResponse>) :
            JuzState()
    }
}