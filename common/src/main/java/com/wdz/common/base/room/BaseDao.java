package com.wdz.common.base.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.wdz.common.room.entity.History;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:13
 */
@Dao
public interface BaseDao<T> {

    /*返回的类型是Long也只能是Long，否则无法通过编译。
       返回的Long值，是指的插入的行id。*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertItems(T... items);


    /*返回的类型为Integer也只能是Integer，否则无法通过编译。
      返回的Integer值，指的是该次操作影响到的总行数，比如该次操作更新了5条，就返回5。*/
    @Delete
    Single<Integer> deleteItem(T... items);

    @Update
    Single<Integer> updateItems(T... items);


}
