package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class TransferRestoreRequest(
    @SerializedName("code")
    @Keep
    val code: String,
)
