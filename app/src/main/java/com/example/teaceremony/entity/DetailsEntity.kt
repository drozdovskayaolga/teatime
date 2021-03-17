package com.example.teaceremony.entity

import androidx.room.*
import java.util.*


@Entity(tableName = "details_table",
    foreignKeys = [
        ForeignKey(
            entity = TypesEntity::class,
            parentColumns =["id"],
            childColumns = ["typeId"],
            onDelete = ForeignKey.CASCADE
        )])
class DetailsEntity(@PrimaryKey @ColumnInfo(name = "drinkId") var id: Int,
                    @ColumnInfo(name = "name") val name: String,
                    @ColumnInfo(name = "info") val info: String,
                    @ColumnInfo(name = "typeId") val typeId: Int)