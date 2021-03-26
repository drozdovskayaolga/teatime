package com.example.teaceremony

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.teaceremony.entity.DetailsEntity
import com.example.teaceremony.entity.IngredientsDetailsCrossRef
import com.example.teaceremony.entity.IngredientsEntity

data class DrinkWithIngredients(
    @Embedded val drink: DetailsEntity,
    @Relation(
        parentColumn = "drinkId",
        entityColumn = "ingredientId",
        associateBy = Junction(IngredientsDetailsCrossRef::class)
    )
    val ingredients: List<IngredientsEntity>
)