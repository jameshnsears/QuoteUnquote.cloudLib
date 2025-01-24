package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Transfer(
    @SerializedName("code")
    @Keep
    val code: String,

    @SerializedName("current")
    @Keep
    var current: List<Current>,

    @SerializedName("favourite")
    @Keep
    val favourites: List<Favourite>,

    @SerializedName("previous")
    @Keep
    var previous: List<Previous>,

    @SerializedName("settings")
    @Keep
    var settings: List<Settings>,
)
