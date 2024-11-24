package com.example.todaylist.model

import androidx.compose.ui.graphics.Color

enum class Priority(val color: Color) {
    LOW(Color.Green),
    MEDIUM(Color.Yellow),
    HIGH(Color.Red)
}
