package com.wdz.ktcommon.room.base

import androidx.room.*
import io.reactivex.Single

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:13
 */
@Dao
interface BaseDao<T> {
    /*返回的类型是Long也只能是Long，否则无法通过编译。
       返回的Long值，是指的插入的行id。*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(vararg items: T): Single<List<Long>>

    /*返回的类型为Integer也只能是Integer，否则无法通过编译。
      返回的Integer值，指的是该次操作影响到的总行数，比如该次操作更新了5条，就返回5。*/
    @Delete
    fun deleteItem(vararg items: T): Single<Int>

    @Update
    fun updateItems(vararg items: T): Single<Int>
}