package com.wdz.common.base.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.wdz.common.room.dao.HistoryDao;
import com.wdz.common.room.entity.History;
import com.wdz.common.room.repository.HistoryRepository;

/**
 * @Author dezhi.wang
 * @Date 2021/2/7 10:13
 */
@Database(entities = {History.class}, version = 1,exportSchema = false)
public abstract class BaseDatabase extends RoomDatabase{
    private static BaseDatabase mInstance;
    private static String DATABASE_NAME = "my_database";

    /**
     * @return
     */
    public abstract HistoryDao historyDao();

    public static BaseDatabase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (BaseDatabase.class) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context.getApplicationContext(), BaseDatabase.class, DATABASE_NAME)
                            .addMigrations()
                            .build();
                }
            }
        }
        return mInstance;
    }

}
