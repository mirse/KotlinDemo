package com.wdz.common.base.room;

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:24
 */
public interface DatabaseOperationListener<T> {

    /**
     * 操作成功
     * @param items
     */
    void onSuccess(T... items);

    /**
     * 操作失败
     */
    void onFailure();
}
