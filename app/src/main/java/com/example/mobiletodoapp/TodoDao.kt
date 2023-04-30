package com.example.mobiletodoapp

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_app_table")
    suspend fun getAllNotes(): List<TodoData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodoData(todoData: TodoData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todoData: TodoData)

    @Query("SELECT * FROM todo_app_table WHERE title LIKE '%' || :name || '%' or body LIKE '%' || :name || '%'")
    suspend fun searchTodo(name: String): List<TodoData>

    @Delete(entity = TodoData::class)
    suspend fun deleteTodo(todoData: TodoData)

}