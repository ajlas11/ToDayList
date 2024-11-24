package com.example.todaylist.model

import androidx.compose.ui.graphics.Color // Import the Color class

data class Category(
    val id: Int, // Unique category ID
    val name: String, // Category name (e.g., "Work", "Personal")
    val color: Color // Associated color for category
)
