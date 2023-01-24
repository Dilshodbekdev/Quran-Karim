package com.example.quranapp.presentation.screen.page_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.juz.remote.dto.JuzResponse
import com.example.quranapp.data.page.remote.dto.PageResponse
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.juz.entity.Ayah
import com.example.quranapp.domain.page.usecase.PageUseCase
import com.example.quranapp.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val useCase: PageUseCase,
    private val helper: NetworkHelper
):ViewModel() {
    private val _pageDetails =
        MutableStateFlow<PageState>(PageState.Init)
    val pageDetails get() = _pageDetails

    fun fetchAyahs(page: Int) {
        if (helper.isNetworkConnected()) {
            viewModelScope.launch {
                useCase.pageDetails(page).onStart {
                    _pageDetails.value = PageState.IsLoading(true)
                }.catch { e ->
                    _pageDetails.value = PageState.IsLoading(false)
                    _pageDetails.value = PageState.ShowToast(e.message.toString())
                }.collect { value ->
                    _pageDetails.value = PageState.IsLoading(false)
                    when (value) {
                        is BaseResult.Success ->
                            _pageDetails.value = PageState.Success(value.data.ayahs)
                        is BaseResult.Error ->
                            _pageDetails.value = PageState.Error(value.rawResponse)
                    }
                }
            }
        }
    }

    sealed class PageState {
        object Init : PageState()
        data class IsLoading(val isLoading: Boolean) : PageState()
        data class ShowToast(val message: String) : PageState()
        data class Success(val entity: List<Ayah>) : PageState()
        data class Error(val rawResponse: WrappedResponse<PageResponse>) :
            PageState()
    }
}