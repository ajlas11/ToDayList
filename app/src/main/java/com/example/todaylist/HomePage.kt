package com.example.todaylist

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todaylist.viewmodel.MainViewModel

@Composable
fun HomePage(
    navController: NavController, // Navigation controller for navigating screens
    viewModel: MainViewModel, // ViewModel to handle task state
    onNavItemClick: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") } // State for search text
    val tasks by viewModel.filteredTasks.collectAsState(initial = emptyList()) // Collect filtered tasks

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_task") }, // Navigate to AddTaskScreen
                containerColor = Color(0xFF3A5FCD)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task",
                    tint = Color.White
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(onNavItemClick = onNavItemClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Tasks",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Create your categorised task boards.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            TextField(
                value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                    viewModel.updateSearchQuery(newText) // Update the search query in ViewModel
                },
                label = { Text("Search Tasks") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Task List
            TaskList(
                tasks = tasks, // Use the collected task list
                onTaskClick = { task ->
                    navController.navigate("edit_task/${task.id}") // Navigate to EditTaskScreen
                }
            )
        }
    }
}

@Composable
fun BottomNavigationBar(onNavItemClick: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { onNavItemClick("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onNavItemClick("calendar") },
            icon = { Icon(Icons.Filled.CalendarToday, contentDescription = "Calendar") },
            label = { Text("Calendar") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onNavItemClick("search") },
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            label = { Text("Search") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onNavItemClick("more") },
            icon = { Icon(Icons.Default.MoreVert, contentDescription = "More") },
            label = { Text("More") }
        )
    }
}
