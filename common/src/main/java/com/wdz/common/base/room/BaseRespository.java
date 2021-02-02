package com.wdz.common.base.room;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:22
 */
public abstract class BaseRespository<T,R extends BaseDao> {
    private R mDao;

    public void insertItems(T... items, final DatabaseOperationListener<T> databaseOperationListener){
        Single single = mDao.insertItems(items);
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Object o) {
                        databaseOperationListener.onSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        databaseOperationListener.onFailure();
                    }
                })

    }
}
