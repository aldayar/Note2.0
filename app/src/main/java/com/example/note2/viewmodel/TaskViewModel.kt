package com.example.note2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.note2.model.Task
import com.example.note2.room.NoteDao
import com.example.note2.room.TaskDataBase

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao: NoteDao
    private val allTasks: LiveData<List<Task>>

    init {
        val database = TaskDataBase.getDatabase(application)
        taskDao = database.taskDao()
        allTasks = taskDao.getAllNote()
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return allTasks
    }

    fun add(title: String, desc: String) {
        val note = Task(title = title, desc = desc)
        taskDao.insert(note)
    }

    fun markNoteAsCompleted(task: Task) {
        taskDao.update(task)
    }

    fun remove(task: Task) {
        taskDao.delete(task)
    }
}
