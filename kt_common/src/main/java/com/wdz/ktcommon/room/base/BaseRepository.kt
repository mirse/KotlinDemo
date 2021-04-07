package com.wdz.ktcommon.room.base


import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 数据库操作基类
 * @Author dezhi.wang
 * @Date 2021/2/1 17:22
 */
abstract class BaseRepository<T, R : BaseDao<T>> {
    var mDao: R? = null



    /**
     * 插入数据
     * @param databaseOperationListener
     * @param items
     */
    fun insertItems(
        databaseOperationListener: DatabaseOperationListener<T>,
        vararg items: T
    ) {
        mDao?.run{
            // TODO: 2021/4/6
            //  vararg:可变参数
            //  *: 星投影，表明不知道关于泛型实参的任何信息， *代表所有类型，相当于Any?
            //  *vararg -> 数组拆分成可变参数
            val single = insertItems(*items)
            execute(single, databaseOperationListener)
        }

    }

    /**
     * 删除数据
     * @param databaseOperationListener
     * @param items
     */
    fun deleteItems(
        databaseOperationListener: DatabaseOperationListener<T>,
        vararg items: T
    ) {
        mDao?.run {
            val single = deleteItem(*items)
            execute(single, databaseOperationListener)
        }

    }

    /**
     * 查询数据
     * @param databaseOperationListener
     * @param items
     */
    fun updateItems(
        databaseOperationListener: DatabaseOperationListener<T>,
        vararg items: T
    ) {
        mDao?.run {
            val single = updateItems(*items)
            execute(single, databaseOperationListener)
        }

    }

    /**
     * 数据库操作执行方法
     * @param single
     * @param databaseOperationListener
     * @param <T>
     * @param <R>
    </R></T> */
    private fun <T, R> execute(
        single: Single<T>,
        databaseOperationListener: DatabaseOperationListener<R>
    ) {
        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<T> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(t: T) {
                    databaseOperationListener.onSuccess(null)
                }

                override fun onError(e: Throwable) {
                    databaseOperationListener.onFailure()
                }
            })
    }
}