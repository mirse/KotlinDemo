package com.wdz.common.room.repository;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.wdz.common.base.room.BaseDatabase;
import com.wdz.common.base.room.BaseRepository;
import com.wdz.common.base.room.DatabaseOperationListener;
import com.wdz.common.net.NetManager;
import com.wdz.common.room.dao.HistoryDao;
import com.wdz.common.room.entity.History;

import java.security.PublicKey;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author dezhi.wang
 * @Date 2021/2/7 10:09
 */
public class HistoryRepository extends BaseRepository<History, HistoryDao> {
    private static HistoryRepository mInstance;
    private final HistoryDao historyDao;
    private LifecycleOwner lifecycleOwner;

    public static HistoryRepository getInstance(Context context,LifecycleOwner lifecycleOwner) {
        if (mInstance == null) {
            synchronized (HistoryRepository.class) {
                if (mInstance == null) {
                    mInstance = new HistoryRepository(context,lifecycleOwner);
                }
            }
        }
        return mInstance;
    }

    public HistoryRepository(Context context,LifecycleOwner lifecycleOwner) {
        historyDao = BaseDatabase.getInstance(context).historyDao();
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public LifecycleOwner bindLifecycleOwner() {
        return lifecycleOwner;
    }

    public void getAllHistory(final DatabaseOperationListener<History> databaseOperationListener){
        Flowable<List<History>> historyListFlowable = historyDao.loadAllHistory();
        execute(historyListFlowable, new Consumer<List<History>>() {
            @Override
            public void accept(List<History> histories) throws Exception {
                databaseOperationListener.onSuccess(histories);
            }
        });

    }



    /**
     * 数据库操作执行方法
     * @param flowable
     * @param consumer
     * @param <T>
     * @param <R>
     */
    private <T,R> void execute(Flowable<T> flowable, Consumer<T> consumer) {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider
                        .from(bindLifecycleOwner(), Lifecycle.Event.ON_DESTROY)))
                .subscribe(consumer);
    }

}
