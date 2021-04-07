package com.wdz.ktcommon.room.base

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:24
 */
interface DatabaseOperationListener<T> {
    /**
     * 操作成功
     * @param items
     */
    fun onSuccess(items: List<T>?)

    /**
     * 操作失败
     */
    fun onFailure()
}