package com.wdz.common.util;

import com.tencent.mmkv.MMKV;

public class MmkvUtils {

    public synchronized static void setCookie(String cookie){
        MMKV mmkv = MMKV.mmkvWithID("cookie", MMKV.MULTI_PROCESS_MODE);
        mmkv.encode("cookie",cookie);
    }

    public synchronized static String getCookie(){
        MMKV mmkv = MMKV.mmkvWithID("cookie", MMKV.MULTI_PROCESS_MODE);
        return mmkv.decodeString("cookie","");
    }
}
