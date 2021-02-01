package com.wdz.common.base.room;

import io.reactivex.Single;

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:22
 */
public abstract class BaseRespository<T,R extends BaseDao> {
    private R mDao;

    public void insertItems(T... items,DatabaseOperationListener<T> databaseOperationListener){
        Single single = mDao.insertItems(items);
    }
}
