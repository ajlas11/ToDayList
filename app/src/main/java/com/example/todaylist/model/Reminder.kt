package com.example.todaylist.model

data class Reminder(
    val id: Int, // Unique reminder ID
    val taskId: Int, // ID of the task this reminder is linked to
    val reminderTime: String // Time for the reminder (e.g., "2023-12-01T10:00:00")
)
