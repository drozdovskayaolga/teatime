package com.example.teaceremony.dao

import androidx.room.*
import com.example.teaceremony.entity.IngredientsEntity

@Dao
interface IngredientsDao {

    @Query("SELECT * FROM ingredients_table")
    suspend fun getAllIngredients():List<IngredientsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: IngredientsEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<IngredientsEntity>)
}