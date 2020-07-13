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
    Long insert(ET entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<ET> arr);

    @Update
    int update(ET entity);

    @Delete
    int delete(ET entity);
}
