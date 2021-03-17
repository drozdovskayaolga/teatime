package com.example.teaceremony.dao

import androidx.room.*
//import com.example.teaceremony.DrinkWithIngredients
import com.example.teaceremony.entity.DetailsEntity

@Dao
interface DetailsDao {

//    @Query("SELECT * FROM details_table ORDER BY info ASC")

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(info: DetailsEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<DetailsEntity>)

    @Query("DELETE FROM details_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM details_table WHERE typeId=:typeId")
    suspend fun getDetailsByType(typeId: Int): List<DetailsEntity>

    @Query("SELECT * FROM details_table WHERE drinkId=:id")
    suspend fun getDetailsById(id: Int): DetailsEntity

//    @Transaction
//    @Query("SELECT * FROM details_table")
//    fun getDrinkWithIngredients(): List<DrinkWithIngredients>
}