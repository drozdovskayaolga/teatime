package com.example.teaceremony.dao

import androidx.room.*
import com.example.teaceremony.DrinkWithIngredients
//import com.example.teaceremony.DrinkWithIngredients
import com.example.teaceremony.entity.DrinksEntity

@Dao
interface DrinksDao {

//    @Query("SELECT * FROM details_table ORDER BY info ASC")

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(info: DrinksEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<DrinksEntity>)

    @Query("DELETE FROM details_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM details_table WHERE typeId=:typeId")
    suspend fun getDetailsByType(typeId: Int): List<DrinksEntity>

    @Query("SELECT * FROM details_table WHERE drinkId=:id")
    suspend fun getDetailsById(id: Int): DrinksEntity

    @Transaction
    @Query("SELECT * FROM details_table")
    fun getDrinkWithIngredients(): List<DrinkWithIngredients>

    @Query("SELECT * FROM details_table d inner join cross_table c on d.drinkId = c.drinkId WHERE c.ingredientId in (:ids) GROUP BY d.drinkId")
    suspend fun getDrinksByIngredients(ids: List<Int>): List<DrinksEntity>

}