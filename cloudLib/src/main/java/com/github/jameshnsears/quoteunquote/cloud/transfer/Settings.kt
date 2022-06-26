package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Settings(
    @SerializedName("quotations")
    @Keep
    val quotations: Quotations,
    @SerializedName("appearance")
    @Keep
    val appearance: Appearance,
    @SerializedName("schedule")
    @Keep
    val schedule: Schedule,
    @SerializedName("widget_id")
    @Keep
    val widgetId: Int
)
