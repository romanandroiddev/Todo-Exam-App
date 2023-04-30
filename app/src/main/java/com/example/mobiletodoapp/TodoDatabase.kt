package com.example.mobiletodoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoData::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    companion object {

        const val DATABASE_NAME = "todo_app_db"

        fun getInstance(context: Context): TodoDatabase {
            return Room.databaseBuilder(
                context, TodoDatabase::class.java, DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}