package com.wdz.ktcommon.base

/**

 * @Author dezhi.wang

 * @Date 2021/3/30 10:51

 */
data class BaseResp<T>(var code: Int = 0, var errorMsg:String = "",var data:T)