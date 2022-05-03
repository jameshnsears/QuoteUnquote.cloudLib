package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Quotations(
    @SerializedName("CONTENT_ADD_TO_PREVIOUS_ALL")
    @Keep
    val contentAddToPreviousAll: Boolean,

    @SerializedName("CONTENT_ALL")
    @Keep
    val contentAll: Boolean,

    @SerializedName("CONTENT_AUTHOR")
    @Keep
    val contentAuthor: Boolean,

    @SerializedName("CONTENT_AUTHOR_NAME")
    @Keep
    val contentAuthorName: String,

    @SerializedName("CONTENT_FAVOURITES")
    @Keep
    val contentFavourites: Boolean,

    @SerializedName("CONTENT_SEARCH")
    @Keep
    val contentSearch: Boolean,

    @SerializedName("CONTENT_SEARCH_COUNT")
    @Keep
    val contentSearchCount: Int,

    @SerializedName("CONTENT_SEARCH_TEXT")
    @Keep
    val contentSearchText: String
)
