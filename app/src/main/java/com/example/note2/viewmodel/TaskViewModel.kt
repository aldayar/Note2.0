package com.example.note2.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.note2.model.Task

class TaskViewModel : ViewModel() {
    private val tasksList = mutableListOf<Task>()
    private val tasksLiveData = MutableLiveData<List<Task>>()

    init {
        tasksLiveData.value = tasksList
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return tasksLiveData
    }

    fun add(title: String, description: String) {
        val task = Task(title, description)
        tasksList.add(task)
        tasksLiveData.value = tasksList
    }

    fun markNoteAsCompleted(task: Task) {
        task.isDone = true
        tasksLiveData.value = tasksList
    }

    fun remove(task: Task) {
        tasksList.remove(task)
        tasksLiveData.value = tasksList
    }
}
