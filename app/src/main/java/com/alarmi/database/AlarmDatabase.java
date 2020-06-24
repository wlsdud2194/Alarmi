package com.alarmi.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Alarm.class}, version = 1)
public abstract class AlarmDatabase extends RoomDatabase {
    public abstract AlarmDAO alarmDAO();

    private static AlarmDatabase instance;
    public AlarmDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context,
                    AlarmDatabase.class,
                    "db").build();
        }
        return instance;
    }
}
