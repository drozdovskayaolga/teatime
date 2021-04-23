package com.example.teaceremony.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teaceremony.dao.DrinksDao
import com.example.teaceremony.dao.IngredientsDao
import com.example.teaceremony.dao.CrossDao
import com.example.teaceremony.dao.TypesDao
import com.example.teaceremony.entity.DrinksEntity
import com.example.teaceremony.entity.CrossEntity
import com.example.teaceremony.entity.IngredientsEntity
import com.example.teaceremony.entity.TypesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

// Annotates class to be a Room Database with a table (entity) of the  class
@Database(
    entities = [TypesEntity::class, DrinksEntity::class, IngredientsEntity::class, CrossEntity::class],
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun typesDao(): TypesDao

    abstract fun drinksDao(): DrinksDao

    abstract fun ingredientsDao(): IngredientsDao

    abstract fun crossDao(): CrossDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                runBlocking {
                    INSTANCE?.let { database ->
                        var typesDao = database.typesDao()
                        var drinksDao = database.drinksDao()
                        var ingredientsDao = database.ingredientsDao()
                        var crossDao = database.crossDao()

                        // Delete all content here.
                        typesDao.deleteAll()
                        drinksDao.deleteAll()

                        // Adding types
                        val tea = TypesEntity(2, "Чай")
                        typesDao.insert(tea)
                        val coffee = TypesEntity(3, "Кофе")
                        typesDao.insert(coffee)
                        val cocktail = TypesEntity(1, "Коктейли")
                        typesDao.insert(cocktail)

                        // Adding list of drinks
                        // TEA
                        drinksDao.insertAll(teaList)

                        // COFFEE
                        drinksDao.insertAll(coffeeList)

                        // COCKTAILS
                        drinksDao.insertAll(cocktailsList)

                        // Adding list of ingredients
                        ingredientsDao.insertAll(ingredientsList)

                        // Cross
                        crossDao.insertAll(crossList)
                    }
                }
                // return instance
                instance
            }
        }
    }
}