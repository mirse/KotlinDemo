package com.wdz.common.util;

import com.wdz.common.BuildConfig;

/**
 * @author wdz
 */
public class Log {
    public static void i(String tag, String msg){
        if (BuildConfig.LOG){
            android.util.Log.i(tag, msg);
        }
    }
    public static void d(String tag, String msg){
        if (BuildConfig.LOG){
            android.util.Log.d(tag, msg);
        }
    }
    public static void e(String tag, String msg){
        if (BuildConfig.LOG){
            android.util.Log.e(tag, msg);
        }
    }
}
