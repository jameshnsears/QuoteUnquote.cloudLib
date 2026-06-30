package com.github.jameshnsears.quoteunquote.cloud.transfer

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Sync(
    @SerializedName("SYNC_AUTO_CLOUD_BACKUP")
    @Keep
    val syncAutoCloudBackup: Boolean,
)
