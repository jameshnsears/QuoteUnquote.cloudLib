package com.github.jameshnsears.quoteunquote.cloud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import timber.log.Timber;

public final class CloudFavourites {
    public static final int TIMEOUT_SECONDS = 10;
    private static final String DNS = "8.8.8.8";
    @Nullable
    private static ExecutorService executorService;

    @NonNull
    public static ExecutorService getExecutorService() {
        if (executorService == null) {
            executorService = Executors.newSingleThreadExecutor();
        }
        Timber.d(executorService.toString());
        return executorService;
    }

    public static void shutdown() {
        if (executorService != null) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                executorService.shutdown();
                try {
                    if (!executorService.awaitTermination(5000, TimeUnit.MICROSECONDS)) {
                        Timber.d("awaitTermination=timeout");
                    }
                } catch (@NonNull InterruptedException e) {
                    Timber.e(e);
                }
                Timber.d(executorService.toString());
            }));
        }
    }

    public boolean save(@NonNull final String payload) {
        final String endpointSave = BuildConfig.REMOTE_DEVICE_ENDPOINT + "/save";
        final Future<Boolean> future = getExecutorService().submit(() -> {

            Request request = new Request.Builder()
                    .url(endpointSave)
                    .post(RequestBody.create(
                            payload,
                            MediaType.get("application/json; charset=utf-8")))
                    .build();

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .build();

            try {
                try (Response response = client.newCall(request).execute()) {
                    return response.isSuccessful();
                }
            } catch (SocketTimeoutException e) {
                return false;
            }
        });

        try {
            return future.get();
        } catch (@NonNull ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Nullable
    public ReceiveResponse receive(final int timeout, @NonNull final String payload) {
        final String endpointLoad = BuildConfig.REMOTE_DEVICE_ENDPOINT + "/receive";

        final Request request = new Request.Builder()
                .url(endpointLoad)
                .post(RequestBody.create(
                        payload,
                        MediaType.get("application/json; charset=utf-8")))
                .build();

        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .build();

        Response response = null;
        ReceiveResponse receiveResponse = null;
        try {
            final ReceiveResponseFuture future = getReceiveResponseFuture();
            client.newCall(request).enqueue(future);

            response = future.get();
            final String responseBody = response.body().string();
            Timber.d("%d", response.code());

            final Gson gson = new Gson();
            receiveResponse = gson.fromJson(responseBody, ReceiveResponse.class);
        } catch (@NonNull ExecutionException | InterruptedException | IOException e) {
            Timber.w(e.toString());
            Thread.currentThread().interrupt();
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return receiveResponse;
    }

    @NotNull
    public ReceiveResponseFuture getReceiveResponseFuture() {
        return new ReceiveResponseFuture();
    }

    public boolean isInternetAvailable() {
        final Future<Boolean> future = getExecutorService().submit(() -> {

            try (Socket socket = getSocket()) {
                socket.connect(new InetSocketAddress(DNS, 53), 1500);
                return true;
            } catch (Exception e) {
                Timber.e(e);
                return false;
            }
        });

        try {
            boolean available = future.get();
            Timber.d("isInternetAvailable=%b", available);
            return available;
        } catch (@NonNull ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @NotNull
    public Socket getSocket() {
        return new Socket();
    }
}
