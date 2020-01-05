package com.example.my_second_app.model.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface PackDao {
    @Insert
    long insert(Pack pack);

    @Update
    void update(Pack pack);

    @Delete
    void delete(Pack pack);

    @Query("DELETE FROM pack_table")
    void deleteAllNotes();

    @Query("SELECT * FROM pack_table ORDER BY priority DESC")
    LiveData<List<Pack>> getAllPacks();
}
