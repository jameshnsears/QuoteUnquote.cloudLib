package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class TransferRestoreResponse(
    @SerializedName("transfer")
    @Keep
    val transfer: Transfer,

    @SerializedName("error")
    @Keep
    val error: String,

    @SerializedName("reason")
    @Keep
    val reason: String
)
