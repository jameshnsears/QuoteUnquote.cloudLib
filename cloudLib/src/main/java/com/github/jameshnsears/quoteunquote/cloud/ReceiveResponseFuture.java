package com.github.jameshnsears.quoteunquote.cloud;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;
import timber.log.Timber;

public class ReceiveResponseFuture extends CompletableFuture<Response> implements Callback {
    @Override
    public void onResponse(@NonNull final Call call, @NonNull final Response response) {
        super.complete(response);

        final Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            Timber.d(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
    }

    @Override
    public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
        super.completeExceptionally(e);
    }
}
