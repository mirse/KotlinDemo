package com.wdz.common.net.http;

import android.util.Log;

import com.wdz.common.util.MmkvUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    private static final String TAG = "ReceivedCookiesIntercep";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers("Set-Cookie").isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            for (String header:response.headers("Set-Cookie")) {
                stringBuilder.append(header);
                if (header.contains("JESSIONID")){
                    String cookie = header.substring(header.indexOf("JESSIONID"),header.indexOf(";"));
                    MmkvUtils.setCookie(cookie);
                }
            }
            Log.i(TAG, "intercept: "+stringBuilder.toString());
        }
        return response;
    }
}
