
package com.example.todaylist.model

import com.example.todaylist.model.Priority
import androidx.compose.ui.graphics.Color

data class Task(
    val id: Int,
    val name: String,
    val priority: Priority,
    val priorityColor: Color
)


fun getPriorityColor(priority: Priority): Color {
    return when (priority) {
        Priority.LOW -> Color.Green
        Priority.MEDIUM -> Color.Yellow
        Priority.HIGH -> Color.Red
    }
}