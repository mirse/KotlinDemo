package com.wdz.ktcommon.constant

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @Author dezhi.wang
 * @Date 2021/4/8 14:01
 */
@Route(path = "/service/json")
class JsonServiceImpl : SerializationService {
    private var mGson: Gson? = null
    override fun init(context: Context) {
        mGson = Gson()
    }

    override fun <T> json2Object(text: String, clazz: Class<T>): T {
        checkJson()
        return mGson!!.fromJson(text, clazz)
    }

    override fun object2Json(instance: Any): String {
        checkJson()
        return mGson!!.toJson(instance)
    }

    override fun <T> parseObject(input: String, clazz: Type): T {
        checkJson()
        return mGson!!.fromJson(input, clazz)
    }

    fun checkJson() {
        if (mGson == null) {
            mGson = Gson()
        }
    }
}