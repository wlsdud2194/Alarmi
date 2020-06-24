package com.alarmi.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlarmDAO extends BaseDAO<Alarm> {
    @Query("SELECT * FROM alarm")
    List<Alarm> findAll();

    @Query("SELECT * FROM alarm WHERE id=:id")
    Alarm findById(Long id);
}
