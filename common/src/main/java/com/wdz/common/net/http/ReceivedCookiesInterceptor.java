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
            //Server: Apache-Coyote/1.1
            //Set-Cookie: JSESSIONID=11BF98DC27BB68F04F89FCAAFC621D8E; Path=/; Secure; HttpOnly
            //Set-Cookie: loginUserName=Menghuanqv; Expires=Fri, 23-Apr-2021 08:48:03 GMT; Path=/
            //Set-Cookie: token_pass=34a3fb5c2910799e9d4e3bb349697359; Expires=Fri, 23-Apr-2021 08:48:03 GMT; Path=/
            //Set-Cookie: loginUserName_wanandroid_com=Menghuanqv; Domain=wanandroid.com; Expires=Fri, 23-Apr-2021 08:48:03 GMT; Path=/
            //Set-Cookie: token_pass_wanandroid_com=34a3fb5c2910799e9d4e3bb349697359; Domain=wanandroid.com; Expires=Fri, 23-Apr-2021 08:48:03 GMT; Path=/
            //Content-Type: application/json;charset=UTF-8
            //Transfer-Encoding: chunked
            //Date: Wed, 24 Mar 2021 08:48:03 GMT



            //Server: Apache-Coyote/1.1
            //Set-Cookie: loginUserName=Menghuanqv; Expires=Fri, 23-Apr-2021 08:44:46 GMT; Path=/
            //Set-Cookie: token_pass=34a3fb5c2910799e9d4e3bb349697359; Expires=Fri, 23-Apr-2021 08:44:46 GMT; Path=/
            //Set-Cookie: loginUserName_wanandroid_com=Menghuanqv; Domain=wanandroid.com; Expires=Fri, 23-Apr-2021 08:44:46 GMT; Path=/
            //Set-Cookie: token_pass_wanandroid_com=34a3fb5c2910799e9d4e3bb349697359; Domain=wanandroid.com; Expires=Fri, 23-Apr-2021 08:44:46 GMT; Path=/
            //Content-Type: application/json;charset=UTF-8
            //Transfer-Encoding: chunked
            //Date: Wed, 24 Mar 2021 08:44:46 GMT
            StringBuilder stringBuilder = new StringBuilder();
            for (String header:response.headers("Set-Cookie")) {
                stringBuilder.append(header);
                Log.i(TAG, "intercept: "+header);
                if (header.contains("JESSIONID")){
                    String cookie = header.substring(header.indexOf("JESSIONID"),header.indexOf(";"));
                    //MmkvUtils.setCookie(cookie);
                }

            }
            MmkvUtils.setCookie(stringBuilder.toString());
            Log.i(TAG, "stringBuilder: "+stringBuilder.toString());
        }
        return response;
    }
}
