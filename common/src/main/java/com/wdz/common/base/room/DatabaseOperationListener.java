package com.wdz.common.base.room;

import java.util.List;

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:24
 */
public interface DatabaseOperationListener<T> {

    /**
     * 操作成功
     * @param items
     */
    void onSuccess(List<T> items);

    /**
     * 操作失败
     */
    void onFailure();
}
