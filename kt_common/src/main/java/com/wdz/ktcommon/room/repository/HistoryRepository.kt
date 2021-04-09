package com.wdz.ktcommon.room.repository

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlin.jvm.Throws

/**
 * @Author dezhi.wang
 * @Date 2021/2/7 10:09
 */
class HistoryRepository(context: Context) : BaseRepository<History, HistoryDao>() {
    private val TAG = this::class.simpleName
    init {
        mDao = BaseDatabase.getInstance(context)?.historyDao()
    }

    companion object {
        private var mInstance: HistoryRepository? = null
        fun getInstance(context: Context): HistoryRepository? {
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



    fun getAllHistory(): Flow<List<History>>? {

        return mDao?.loadAllHistory()

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