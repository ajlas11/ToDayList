package com.example.todaylist
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todaylist.viewmodel.MainViewModel
import com.example.todaylist.model.Priority
import com.example.todaylist.model.Task

@Composable
fun AddTaskScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onSave: () -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var taskPriority by remember { mutableStateOf(Priority.LOW) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Task Name Input
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Priority Dropdown
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { isDropdownExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Priority: ${taskPriority.name}")
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
                    viewModel.addTask(
                        Task(
                            id = System.currentTimeMillis().toInt(),
                            name = taskName,
                            priority = taskPriority,
                            priorityColor = taskPriority.color
                        )
                    )
                    onSave()
                } else {
                    // Handle empty task name
                    println("Task name cannot be empty")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Task")
        }
    }
}
