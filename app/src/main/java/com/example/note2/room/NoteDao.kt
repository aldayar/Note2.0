package com.example.note2.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.note2.model.Task

@Dao
interface NoteDao {

    @Query("select * from task")
    fun getAllNote(): LiveData<List<Task>>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}