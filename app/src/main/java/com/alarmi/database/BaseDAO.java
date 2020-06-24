package com.alarmi.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BaseDAO<ET> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ET insert(ET entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    ET insert(List<ET> arr);

    @Update
    ET update(ET entity);

    @Delete
    boolean delete(ET entity);
}
