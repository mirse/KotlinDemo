package com.wdz.ktcommon.room.repository

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.wdz.ktcommon.room.base.BaseDao
import com.wdz.ktcommon.room.base.BaseDatabase
import com.wdz.ktcommon.room.base.BaseRepository
import com.wdz.ktcommon.room.base.DatabaseOperationListener
import com.wdz.ktcommon.room.dao.HistoryDao
import com.wdz.ktcommon.room.entity.History
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlin.jvm.Throws

/**
 * @Author dezhi.wang
 * @Date 2021/2/7 10:09
 */
class HistoryRepository(context: Context) : BaseRepository<History, HistoryDao>() {
    init {
        mDao = BaseDatabase.getInstance(context)?.historyDao()
    }

    companion object {
        private var mInstance: HistoryRepository? = null
        fun getInstance(context: Context, ): HistoryRepository? {
            if (mInstance == null) {
                synchronized(HistoryRepository::class.java) {
                    if (mInstance == null) {
                        mInstance = HistoryRepository(context)
                    }
                }
            }
            return mInstance
        }
    }



    fun getAllHistory(databaseOperationListener: DatabaseOperationListener<History>) {
        mDao?.run {
            execute<List<History>, Any>(loadAllHistory(), object : Consumer<List<History>> {
                override fun accept(histories: List<History>) {
                    databaseOperationListener.onSuccess(histories)
                }
            })
        }

    }

    /**
     * 数据库操作执行方法
     * @param flowable
     * @param consumer
     * @param <T>
     * @param <R>
    </R></T> */
    private fun <T, R> execute(
        flowable: Flowable<T>,
        consumer: Consumer<T>
    ) {
        flowable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(consumer)
    }


}