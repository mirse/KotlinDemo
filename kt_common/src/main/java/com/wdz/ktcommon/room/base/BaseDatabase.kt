package com.wdz.ktcommon.room.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wdz.ktcommon.room.dao.HistoryDao
import com.wdz.ktcommon.room.entity.History

/**
 * @Author dezhi.wang
 * @Date 2021/2/7 10:13
 */
@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class BaseDatabase : RoomDatabase() {
    /**
     * @return
     */
    abstract fun historyDao(): HistoryDao

    companion object {
        private var mInstance: BaseDatabase? = null
        private const val DATABASE_NAME = "my_database"
        fun getInstance(context: Context): BaseDatabase? {
            if (mInstance == null) {
                synchronized(BaseDatabase::class.java) {
                    if (mInstance == null) {
                        mInstance = Room.databaseBuilder(context, BaseDatabase::class.java, DATABASE_NAME)
                                .addMigrations()
                                .build()
                    }
                }
            }
            return mInstance
        }
    }
}