package com.wdz.common.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:07
 */
@Entity(tableName = "history_tab")
public class History {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "search_info")
    public String searchTitle;

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", searchTitle='" + searchTitle + '\'' +
                '}';
    }
}
