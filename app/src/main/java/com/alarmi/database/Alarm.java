package com.alarmi.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "alarm")
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private LocalDateTime time;

    private boolean actived;

    private String memo;
}
