package com.github.jameshnsears.quoteunquote.cloud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import timber.log.Timber;


public class CloudFavouritesHelper {
    @Nullable
    private static String localCode;

    private CloudFavouritesHelper() {
        throw new IllegalStateException("Utility class");
    }

    @NonNull
    public static synchronized String getLocalCode() {
        if (localCode == null) {
            final String rootCode = RandomStringUtils.randomAlphanumeric(8);
            final String crc = new String(Hex.encodeHex(DigestUtils.md5(rootCode)));
            localCode = rootCode + crc.substring(0, 2);
        }
        return localCode;
    }

    public static boolean isRemoteCodeValid(@NonNull final String remoteCode) {
        final String rootCode = remoteCode.substring(0, 8);
        final String expectedCRC = new String(Hex.encodeHex(DigestUtils.md5(rootCode)));
        return remoteCode.endsWith(expectedCRC.substring(0, 2));
    }

    @NonNull
    public static String jsonReceiveRequest(@NonNull final String remoteCode) {
        ReceiveRequest receiveRequest = new ReceiveRequest();
        receiveRequest.code = remoteCode;

        String json = getGson().toJson(receiveRequest);
        Timber.d("json=%s", json);
        return json;
    }

    @NonNull
    public static String jsonSendRequest(@NonNull final SaveRequest saveRequest) {
        String json = getGson().toJson(saveRequest);
        Timber.d("json=%s", json);
        return json;
    }

    @NonNull
    private static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        return builder.create();
    }
}
