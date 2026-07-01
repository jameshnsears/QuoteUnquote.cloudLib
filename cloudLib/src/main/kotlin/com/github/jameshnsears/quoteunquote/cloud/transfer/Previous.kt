package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Previous(
    @SerializedName("content_type")
    @Keep
    val contentType: Int,

    @SerializedName("digest")
    @Keep
    val digest: String,

    @SerializedName("navigation")
    @Keep
    val navigation: Int,

    @SerializedName("widget_id")
    @Keep
    val widgetId: Int,

    @SerializedName("db")
    @Keep
    val db: String,
)
