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

    @SerializedName("CONTENT_ALL_EXCLUSION")
    @Keep
    val contentAllExclusion: String,

    @SerializedName("CONTENT_AUTHOR")
    @Keep
    val contentAuthor: Boolean,

    @SerializedName("CONTENT_AUTHOR_NAME_COUNT")
    @Keep
    val contentAuthorNameCount: Int,

    @SerializedName("CONTENT_AUTHOR_NAME")
    @Keep
    val contentAuthorName: String,

    @SerializedName("CONTENT_FAVOURITES")
    @Keep
    val contentFavourites: Boolean,

    @SerializedName("CONTENT_SEARCH")
    @Keep
    val contentSearch: Boolean,

    @SerializedName("CONTENT_SEARCH_FAVOURITES_ONLY")
    @Keep
    val contentSearchFavouritesOnly: Boolean,

    @SerializedName("CONTENT_SEARCH_COUNT")
    @Keep
    val contentSearchCount: Int,

    @SerializedName("CONTENT_SEARCH_TEXT")
    @Keep
    val contentSearchText: String,

    @SerializedName("DATABASE_INTERNAL")
    @Keep
    val databaseInternal: Boolean,

    @SerializedName("DATABASE_EXTERNAL")
    @Keep
    val databaseExternal: Boolean,

    @SerializedName("DATABASE_EXTERNAL_WEB")
    @Keep
    val databaseExternalWeb: Boolean,

    @SerializedName("DATABASE_WEB_URL")
    @Keep
    val databaseWebUrl: String,

    @SerializedName("DATABASE_WEB_XPATH_QUOTATION")
    @Keep
    val databaseWebXpathQuotation: String,

    @SerializedName("DATABASE_WEB_XPATH_SOURCE")
    @Keep
    val databaseWebXpathSource: String,

    @SerializedName("DATABASE_WEB_KEEP_LATEST_ONLY")
    @Keep
    val databaseWebKeepLatestOnly: Boolean,
)
