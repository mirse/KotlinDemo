package com.wdz.ktcommon.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wdz.ktcommon.room.base.BaseDao
import com.wdz.ktcommon.room.entity.History
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:13
 */
@Dao
interface HistoryDao : BaseDao<History> {
    @Query("SELECT * FROM HISTORY_TAB")
    fun loadAllHistory(): Flow<List<History>>
}