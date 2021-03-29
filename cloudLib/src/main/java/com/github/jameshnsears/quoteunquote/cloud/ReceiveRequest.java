package com.github.jameshnsears.quoteunquote.cloud;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class ReceiveRequest {
    @Nullable
    @SerializedName("code")
    @Keep
    public String code;
}
