package com.example.teaceremony.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.teaceremony.entity.CrossEntity

@Dao
interface CrossDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<CrossEntity>)
}