package com.gym.data.room.x

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "x",
    foreignKeys = [
//        ForeignKey(
//            entity = yEntity::class,
//            parentColumns = ["x.id"],
//            childColumns = ["y.id_x"],
//            onDelete = ForeignKey.CASCADE
//        )
    ]
)
data class XEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)