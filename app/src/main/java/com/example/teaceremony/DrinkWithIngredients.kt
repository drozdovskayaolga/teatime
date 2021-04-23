package com.example.teaceremony

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.teaceremony.entity.DrinksEntity
import com.example.teaceremony.entity.CrossEntity
import com.example.teaceremony.entity.IngredientsEntity

data class DrinkWithIngredients(
    @Embedded val drink: DrinksEntity,
    @Relation(
        parentColumn = "drinkId",
        entityColumn = "ingredientId",
        associateBy = Junction(CrossEntity::class)
    )
    val ingredients: List<IngredientsEntity>
)