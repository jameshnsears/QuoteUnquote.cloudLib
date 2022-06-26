package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Transfer(
    @SerializedName("code")
    @Keep
    val code: String,

    @SerializedName("current")
    @Keep
    val current: List<Current>,

    @SerializedName("favourite")
    @Keep
    val favourites: List<Favourite>,

    @SerializedName("previous")
    @Keep
    val previous: List<Previous>,

    @SerializedName("settings")
    @Keep
    val settings: List<Settings>
)
