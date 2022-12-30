package com.example.appsl43018255.data.response.todos

data class TodosResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
):java.io.Serializable