package com.example.todaylist.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.ui.graphics.Color
import com.example.todaylist.model.Priority
import com.example.todaylist.model.Task
import androidx.lifecycle.viewModelScope


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    // Mutable state for UI state
    private val _uiState = MutableStateFlow("Default UI State")
    val uiState: StateFlow<String> = _uiState

    // Function to update the UI state
    fun updateState(newState: String) {
        _uiState.value = newState
    }

    // StateFlow to hold the list of tasks
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    // StateFlow for search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    // Computed StateFlow for filtered tasks
    val filteredTasks: StateFlow<List<Task>> = combine(_tasks, _searchQuery) { taskList, query ->
        if (query.isBlank()) {
            taskList
        } else {
            taskList.filter { it.name.contains(query, ignoreCase = true) }
        }
    }.stateIn(
        viewModelScope,
        kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    // Function to update the search query
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Function to add a new task
    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task
    }

    // Function to retrieve a task by ID (or null if not found)
    fun getTaskById(taskId: Int): Task? {
        return _tasks.value.find { it.id == taskId }
    }

    // Function to update a task
    fun updateTask(updatedTask: Task) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == updatedTask.id) {
                updatedTask
            } else {
                task
            }
        }
    }

    // Example for testing (optional):
    init {
        // Adding some initial tasks for testing purposes
        _tasks.value = listOf(
            Task(id = 1, name = "Groceries", priority = Priority.LOW, priorityColor = getPriorityColor(Priority.LOW)),
            Task(id = 2, name = "Work", priority = Priority.HIGH, priorityColor = getPriorityColor(Priority.HIGH)),
            Task(id = 3, name = "Call Mom", priority = Priority.MEDIUM, priorityColor = getPriorityColor(Priority.MEDIUM))
        )
    }

    // Helper function to map priority to a color
    private fun getPriorityColor(priority: Priority): Color {
        return when (priority) {
            Priority.LOW -> Color.Green
            Priority.MEDIUM -> Color.Yellow
            Priority.HIGH -> Color.Red
        }
    }
}
