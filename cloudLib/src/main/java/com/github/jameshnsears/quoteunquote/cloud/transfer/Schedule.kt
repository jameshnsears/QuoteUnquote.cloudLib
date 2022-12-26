package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("EVENT_NEXT_RANDOM")
    @Keep
    val eventNextRandom: Boolean,

    @SerializedName("EVENT_NEXT_SEQUENTIAL")
    @Keep
    val eventNextSequential: Boolean,

    @SerializedName("EVENT_DISPLAY_WIDGET")
    @Keep
    val eventDisplayWidget: Boolean,

    @SerializedName("EVENT_DISPLAY_WIDGET_AND_NOTIFICATION")
    @Keep
    val eventDisplayAidgetAndNotification: Boolean,

    @SerializedName("EVENT_DAILY")
    @Keep
    val eventDaily: Boolean,

    @SerializedName("EVENT_DEVICE_UNLOCK")
    @Keep
    val eventDeviceUnlock: Boolean,

    @SerializedName("EVENT_DAILY_MINUTE")
    @Keep
    val eventDailyMinute: Int,

    @SerializedName("EVENT_DAILY_HOUR")
    @Keep
    val eventDailyHour: Int,

    @SerializedName("EVENT_BIHOURLY")
    @Keep
    val eventEventBihourly: Boolean,
)
