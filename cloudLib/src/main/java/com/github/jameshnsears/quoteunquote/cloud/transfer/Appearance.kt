package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Appearance(
    @SerializedName("APPEARANCE_TRANSPARENCY")
    @Keep
    val appearanceTransparency: Int,

    @SerializedName("APPEARANCE_COLOUR")
    @Keep
    val appearanceColour: String,

    @SerializedName("APPEARANCE_TEXT_FAMILY")
    @Keep
    val appearanceTextFamily: String,

    @SerializedName("APPEARANCE_TEXT_STYLE")
    @Keep
    val appearanceTextStyle: String,

    @SerializedName("APPEARANCE_TEXT_SIZE")
    @Keep
    val appearanceTextSize: Int,

    @SerializedName("APPEARANCE_TEXT_COLOUR")
    @Keep
    val appearanceTextColour: String,

    @SerializedName("APPEARANCE_TOOLBAR_COLOUR")
    @Keep
    val appearanceToolbarColour: String,

    @SerializedName("APPEARANCE_TOOLBAR_FIRST")
    @Keep
    val appearanceToolbarFirst: Boolean,

    @SerializedName("APPEARANCE_TOOLBAR_PREVIOUS")
    @Keep
    val appearanceToolbarPrevious: Boolean,

    @SerializedName("APPEARANCE_TOOLBAR_FAVOURITE")
    @Keep
    val appearanceToolbarFavourite: Boolean,

    @SerializedName("APPEARANCE_TOOLBAR_SHARE")
    @Keep
    val appearanceToolbarShare: Boolean,

    @SerializedName("APPEARANCE_TOOLBAR_RANDOM")
    @Keep
    val appearanceToolbarRandom: Boolean,

    @SerializedName("APPEARANCE_TOOLBAR_SEQUENTIAL")
    @Keep
    val appearanceToolbarSequential: Boolean,
)