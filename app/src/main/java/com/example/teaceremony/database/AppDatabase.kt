package com.example.teaceremony.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teaceremony.dao.DetailsDao
import com.example.teaceremony.dao.IngredientsDao
import com.example.teaceremony.dao.IngredientsDetailsCrossDao
import com.example.teaceremony.dao.TypesDao
import com.example.teaceremony.entity.DetailsEntity
import com.example.teaceremony.entity.IngredientsDetailsCrossRef
import com.example.teaceremony.entity.IngredientsEntity
import com.example.teaceremony.entity.TypesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the  class
@Database(
    entities = [TypesEntity::class, DetailsEntity::class, IngredientsEntity::class, IngredientsDetailsCrossRef::class],
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun typesDao(): TypesDao

    abstract fun detailsDao(): DetailsDao

    abstract fun ingredientsDao(): IngredientsDao

    abstract fun ingredientsDetailsCrossDao(): IngredientsDetailsCrossDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var typesDao = database.typesDao()
                    var detailsDao = database.detailsDao()
                    var ingredientsDao = database.ingredientsDao()
                    var ingredientsDetailsCrossDao = database.ingredientsDetailsCrossDao()

                    // Delete all content here.
                    typesDao.deleteAll()
                    detailsDao.deleteAll()

                    // Adding types
                    val tea = TypesEntity(1, "Чай")
                    typesDao.insert(tea)
                    val coffee = TypesEntity(2, "Кофе")
                    typesDao.insert(coffee)
                    val cocktail = TypesEntity(3, "Коктейли")
                    typesDao.insert(cocktail)

                    // Adding list of drinks
                    // TEA
                    detailsDao.insertAll(teaList)

                    // COFFEE
                    detailsDao.insertAll(coffeeList)

                    // COCKTAILS
                    detailsDao.insertAll(cocktailsList)

                    // Adding list of ingredients
                    ingredientsDao.insertAll(ingredientsList)

                    // Cross
                    ingredientsDetailsCrossDao.insertAll(crossList)
                }
            }
        }
    }

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
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}