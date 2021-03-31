package com.wdz.ktcommon.base

import java.lang.Exception

// TODO: 2021/3/30 out in对应 <? extends T> 和 <? super T>
/**

 * @Author dezhi.wang

 * @Date 2021/3/30 16:15

 */
// TODO: 2021/3/30 sealed密封类
sealed class HttpResult<out T:Any> {
   
    data class Success<out T:Any>(val data:T):HttpResult<T>()
    data class Error(val exception: Exception):HttpResult<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success -> {
                "Success,data=$data"
            }
            is Error ->{
                "Error,exception=$exception"
            }
        }
    }


}