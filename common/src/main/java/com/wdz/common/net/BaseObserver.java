package com.wdz.common.net;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.wdz.common.net.base.BaseResponse;
import com.wdz.common.net.http.ApiException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements SingleObserver<BaseResponse<T>> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onSuccess(@NonNull BaseResponse<T> tBaseResponse) {
        if (tBaseResponse.getErrorCode() == 0){
            onRequestSuccess(tBaseResponse.getData());
        }
        else {
            onRequestError(tBaseResponse.getErrorCode(),tBaseResponse.getErrorMsg());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        String error = null;
        if (e instanceof ConnectException){
            error = "连接服务器错误,请检查网络";
        }
        else if (e instanceof HttpException){
            error = e.getLocalizedMessage();
        }
        else if (e instanceof ApiException){

        }
        else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            error = "服务器数据解析错误";
        }
        else if (e instanceof IOException){
            if (e instanceof SocketException){
                error = "连接服务器超时,请检查网络";
            }
            else{
                error = "连接服务器错误,请检查网络";
            }
        }
        else {
            error = "未知错误";
        }
        onRequestFailure(error);

    }




    protected abstract void onRequestSuccess(T t);
    protected abstract void onRequestError(int errorCode,String errorMsg);
    protected abstract void onRequestFailure(String errorMsg);
}
