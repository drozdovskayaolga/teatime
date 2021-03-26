package com.example.teaceremony.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients_table")
class IngredientsEntity(
    @PrimaryKey @ColumnInfo(name = "ingredientId") val id: Int,
    @ColumnInfo(name = "name") val name: String
){
    @Ignore var isChecked: Boolean = false
}