package com.github.jameshnsears.quoteunquote.cloud;

import androidx.annotation.Nullable;

import java.util.List;

public class ReceiveResponse {
    @Nullable
    public List<String> digests;

    @Nullable
    public String error;

    @Nullable
    public String reason;
}
