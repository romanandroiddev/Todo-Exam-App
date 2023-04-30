package com.example.mobiletodoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_app_table")
data class TodoData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val body: String,
)