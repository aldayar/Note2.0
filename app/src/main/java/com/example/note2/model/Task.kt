package com.example.note2.model


data class Task(
    val title: String,
    val desc: String,
    var isDone: Boolean = false
)