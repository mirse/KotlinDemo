package com.wdz.common.net.http;

import android.util.Log;

import com.wdz.common.util.MmkvUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Log.i(TAG, "intercept: "+MmkvUtils.getCookie());
        builder.addHeader("Cookie", MmkvUtils.getCookie());
        return chain.proceed(builder.build());
    }
}
