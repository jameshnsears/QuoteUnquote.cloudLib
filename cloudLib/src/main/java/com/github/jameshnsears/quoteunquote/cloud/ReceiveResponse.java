package com.github.jameshnsears.quoteunquote.cloud;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiveResponse {
    @Nullable
    @SerializedName("digests")
    @Keep
    public List<String> digests;

    @Nullable
    @SerializedName("error")
    @Keep
    public String error;

    @Nullable
    @SerializedName("reason")
    @Keep
    public String reason;
}
