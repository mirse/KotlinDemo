package com.wdz.ktcommon.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author dezhi.wang
 * @Date 2021/2/1 17:07
 */
@Entity(tableName = "history_tab")
class History {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "search_info")
    var searchTitle: String? = null
    override fun toString(): String {
        return "History{" +
                "id=" + id +
                ", searchTitle='" + searchTitle + '\'' +
                '}'
    }
}