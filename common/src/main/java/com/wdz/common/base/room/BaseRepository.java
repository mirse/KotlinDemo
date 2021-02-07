package com.wdz.common.base.room;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据库操作基类
 * @Author dezhi.wang
 * @Date 2021/2/1 17:22
 */
public abstract class BaseRepository<T,R extends BaseDao> {
    private R mDao;

    public abstract LifecycleOwner bindLifecycleOwner();

    private AutoDisposeConverter<T> bindAutoDispose(LifecycleOwner lifecycleOwner) {
        return AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider
                .from(lifecycleOwner, Lifecycle.Event.ON_DESTROY));
    }

    /**
     * 插入数据
     * @param databaseOperationListener
     * @param items
     */
    public void insertItems(DatabaseOperationListener<T> databaseOperationListener, T... items){
        Single<List<Long>> single = mDao.insertItems(items);
        execute(single,databaseOperationListener);

    }

    /**
     * 删除数据
     * @param databaseOperationListener
     * @param items
     */
    public void deleteItems(DatabaseOperationListener<T> databaseOperationListener, T... items){
        Single<Integer> single = mDao.deleteItem(items);
        execute(single,databaseOperationListener);
    }

    /**
     * 查询数据
     * @param databaseOperationListener
     * @param items
     */
    public void updateItems(DatabaseOperationListener<T> databaseOperationListener, T... items){
        Single<Integer> single = mDao.updateItems(items);
        execute(single,databaseOperationListener);
    }

    /**
     * 数据库操作执行方法
     * @param single
     * @param databaseOperationListener
     * @param <T>
     * @param <R>
     */
    private <T,R> void execute(Single<T> single, final DatabaseOperationListener<R> databaseOperationListener) {
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider
                        .from(bindLifecycleOwner(), Lifecycle.Event.ON_DESTROY)))
                .subscribe(new SingleObserver<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull T t) {
                        databaseOperationListener.onSuccess(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        databaseOperationListener.onFailure();
                    }
                });
    }


}
