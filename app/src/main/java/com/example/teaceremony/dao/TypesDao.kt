package com.example.teaceremony.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teaceremony.entity.TypesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TypesDao {

    @Query("SELECT * FROM types_table")
    suspend fun getTypes(): List<TypesEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: TypesEntity)

    @Query("DELETE FROM types_table")
    suspend fun deleteAll()
}