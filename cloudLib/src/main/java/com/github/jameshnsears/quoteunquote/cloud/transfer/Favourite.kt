package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Favourite(
    @SerializedName("digest")
    @Keep
    val digest: String,

    @SerializedName("navigation")
    @Keep
    val navigation: Int,

    @SerializedName("db")
    @Keep
    val db: String
)
