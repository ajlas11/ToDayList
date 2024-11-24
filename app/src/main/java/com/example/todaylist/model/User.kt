package com.example.todaylist.model

data class User(
    val id: Int, // Unique user ID
    val name: String, // User's full name
    val email: String, // User's email
    val password: String // User's password (encrypted in production)
)
