package com.example.getitdone.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.getitdone.Entities.Task

@Dao
interface TaskDao {

    @Insert
    fun createTask(task: Task)

    @Query("SELECT * FROM task")
    fun getAllTasks() : List<Task>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

}