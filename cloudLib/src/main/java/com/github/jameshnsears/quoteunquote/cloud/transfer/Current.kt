package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("digest")
    @Keep
    val digest: String,

    @SerializedName("widget_id")
    @Keep
    val widgetId: Int,

    @SerializedName("db")
    @Keep
    val db: String,
)
