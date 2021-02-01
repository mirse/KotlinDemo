package com.wdz.common.base.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import io.reactivex.Single;

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:13
 */
@Dao
public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Single<Integer> insertItems(T... items);

    @Delete
    public void deleteItem(T... items);

    @Update
    public void updateItems(T... items);
}
