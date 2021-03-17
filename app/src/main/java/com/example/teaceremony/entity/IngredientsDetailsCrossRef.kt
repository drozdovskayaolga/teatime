package com.example.teaceremony.entity

import androidx.room.Entity

@Entity(primaryKeys = ["drinkId", "ingredientId"])
data class IngredientsDetailsCrossRef (
    val drinkId: Int,
    val ingredientId: Int
)