package com.example.note2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note2.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDataBase: RoomDatabase() {
    abstract fun taskDao(): NoteDao

    companion object {
        private var INSTANCE: TaskDataBase? = null

        fun getDatabase(context: Context): TaskDataBase {
            if (INSTANCE == null) {
                synchronized(TaskDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDataBase::class.java,
                        "task_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}