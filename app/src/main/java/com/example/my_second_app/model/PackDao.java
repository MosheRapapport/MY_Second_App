package com.example.my_second_app.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.my_second_app.entities.PackShow;

import java.util.List;
@Dao
public interface PackDao {
    @Insert
    long insert(PackShow packShow);

    @Update
    void update(PackShow packShow);

    @Delete
    void delete(PackShow packShow);

    @Query("DELETE FROM pack_show_table")
    void deleteAllPacksShow();

    @Query("DELETE  FROM pack_show_table WHERE aKey = :AKey")
    void deletePacksShow(String AKey);

    @Query("SELECT * FROM pack_show_table")
    LiveData<List<PackShow>> getAllPacksShow();

    @Query("SELECT * FROM pack_show_table WHERE packStatus = 1")
    LiveData<List<PackShow>> getAll_OFFER_TO_COLLECT_PacksShow();
}
