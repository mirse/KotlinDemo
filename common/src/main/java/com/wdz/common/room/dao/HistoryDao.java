package com.wdz.common.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.wdz.common.base.room.BaseDao;
import com.wdz.common.room.entity.History;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:13
 */
@Dao
public interface HistoryDao extends BaseDao<History> {

    @Query("SELECT * FROM HISTORY_TAB")
    Flowable<List<History>> loadAllHistory();
}
