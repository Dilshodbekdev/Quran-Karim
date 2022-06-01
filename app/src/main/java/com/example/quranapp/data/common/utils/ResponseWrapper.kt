package com.example.quranapp.data.common.utils

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WrappedListResponse<T>(
    var code: Int,
    @SerializedName("status") var status: String,
    @SerializedName("errors") var errors: List<String>? = null,
    @SerializedName("data") var data: List<T>? = null,
)

@Keep
data class WrappedResponse<T>(
    var code: Int,
    @SerializedName("status") var status: String,
    @SerializedName("data") var data: T? = null,
)