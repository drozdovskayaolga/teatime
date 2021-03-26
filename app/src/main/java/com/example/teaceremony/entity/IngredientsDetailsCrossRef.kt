package com.example.teaceremony.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "cross_table",
    primaryKeys = ["drinkId", "ingredientId"], foreignKeys = [
        ForeignKey(
            entity = DetailsEntity::class,
            parentColumns = ["drinkId"],
            childColumns = ["drinkId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IngredientsEntity::class,
            parentColumns = ["ingredientId"],
            childColumns = ["ingredientId"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class IngredientsDetailsCrossRef(
    @ColumnInfo(name = "drinkId")
    val drinkId: Int,
    @ColumnInfo(name = "ingredientId")
    val ingredientId: Int
)
