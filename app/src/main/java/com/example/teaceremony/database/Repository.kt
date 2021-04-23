package com.example.teaceremony.database

import com.example.teaceremony.entity.DrinksEntity
import com.example.teaceremony.entity.IngredientsEntity
import com.example.teaceremony.entity.TypesEntity

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class Repository(private val appDatabase: AppDatabase) {


    suspend fun insert(drink: TypesEntity, info: DrinksEntity, ingredient: IngredientsEntity) {
        appDatabase.typesDao().insert(drink)
        appDatabase.drinksDao().insert(info)
        appDatabase.ingredientsDao().insert(ingredient)
    }

    suspend fun getAllTypes(): List<TypesEntity> {
        return appDatabase.typesDao().getTypes()
    }

    suspend fun getDetails(typeId: Int): List<DrinksEntity> {
        return appDatabase.drinksDao().getDetailsByType(typeId)
    }

    suspend fun getDrinksByIngredients(ids: List<Int>):List<DrinksEntity> {
        return appDatabase.drinksDao().getDrinksByIngredients(ids)
    }

    suspend fun getDetailsById(id: Int): DrinksEntity {
        return appDatabase.drinksDao().getDetailsById(id)
    }

    suspend fun getAllIngredients(): List<IngredientsEntity> {
        return appDatabase.ingredientsDao().getAllIngredients()
    }
}