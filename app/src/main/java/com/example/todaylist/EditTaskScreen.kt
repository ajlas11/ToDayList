package com.example.todaylist
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todaylist.viewmodel.MainViewModel
import com.example.todaylist.model.Priority
import com.example.todaylist.model.Task

@Composable
fun EditTaskScreen(
    taskId: Int?,
    viewModel: MainViewModel = hiltViewModel(),
    onSave: () -> Unit
) {
    // Retrieve the task from ViewModel using taskId
    val task = taskId?.let { viewModel.getTaskById(it) }

    if (task == null) {
        // If task not found, display an error message
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Task not found!")
            Button(onClick = onSave) {
                Text("Go Back")
            }
        }
        return
    }

    // Local state for editing
    var taskName by remember { mutableStateOf(task.name) }
    var taskPriority by remember { mutableStateOf(task.priority) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // TextField for editing task name
        TextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Priority Dropdown Menu
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { isDropdownExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Priority: ${taskPriority.name}")
            }
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                DropdownMenu(
                    expanded = isDropdownExpanded,
                    onDismissRequest = { isDropdownExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Low Priority") },
                        onClick = {
                            taskPriority = Priority.LOW
                            isDropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Medium Priority") },
                        onClick = {
                            taskPriority = Priority.MEDIUM
                            isDropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("High Priority") },
                        onClick = {
                            taskPriority = Priority.HIGH
                            isDropdownExpanded = false
                        }
                    )
                }

            }
        }

        // Save Button
        Button(
            onClick = {
                if (taskName.isNotBlank()) {
                    viewModel.updateTask(
                        task.copy(name = taskName, priority = taskPriority)
                    )
                    onSave() // Navigate back after saving
                } else {
                    // Optionally handle empty task name
                    println("Task name cannot be empty")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }
    }
}
