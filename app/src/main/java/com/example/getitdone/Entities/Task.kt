package com.example.getitdone.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(

    @PrimaryKey(autoGenerate = true)
    val taskId: Int = 0,

    val title: String,

    val description: String? = null,

    @ColumnInfo(name = "is_starred")
    val isStarred: Boolean = false,

    @ColumnInfo(name = "is_completed")
    val isCompeted: Boolean = false
)
