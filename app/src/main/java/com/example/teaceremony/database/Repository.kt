package com.example.teaceremony.database

import com.example.teaceremony.entity.DetailsEntity
import com.example.teaceremony.entity.IngredientsEntity
import com.example.teaceremony.entity.TypesEntity

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class Repository(private val appDatabase: AppDatabase) {


    suspend fun insert(drink: TypesEntity, info: DetailsEntity, ingredient: IngredientsEntity) {
        appDatabase.typesDao().insert(drink)
        appDatabase.detailsDao().insert(info)
        appDatabase.ingredientsDao().insert(ingredient)
    }

    suspend fun getAllTypes(): List<TypesEntity> {
        return appDatabase.typesDao().getTypes()
    }

    suspend fun getDetails(typeId: Int): List<DetailsEntity> {
        return appDatabase.detailsDao().getDetailsByType(typeId)
    }

    suspend fun getDetailsById(id: Int): DetailsEntity {
        return appDatabase.detailsDao().getDetailsById(id)
    }

    suspend fun getAllIngredients():List<IngredientsEntity>{
        return appDatabase.ingredientsDao().getAllIngredients()
    }
}