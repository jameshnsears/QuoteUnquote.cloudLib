package com.github.jameshnsears.quoteunquote.cloud;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveRequest {
    @Nullable
    @SerializedName("code")
    @Keep
    public String code;

    @Nullable
    @SerializedName("digests")
    @Keep
    public List<String> digests;
}
