package com.example.todaylist

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todaylist.viewmodel.MainViewModel

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "login") {
        // Login Screen
        composable("login") {
            LoginScreen(
                onSignUpClick = { navController.navigate("register") },
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // Remove login from back stack
                    }
                }
            )
        }

        // Sign Up Screen
        composable("register") {
            SignUpScreen(
                onSignUpSuccess = { navController.navigate("login") }
            )
        }

        // Home Page
        composable("home") {
            val viewModel = hiltViewModel<MainViewModel>() // Initialize ViewModel with Hilt
            HomePage(
                navController = navController,
                viewModel = viewModel, // Pass ViewModel to HomePage
                onNavItemClick = { destination -> // Handle bottom navigation items
                    when (destination) {
                        "home" -> println("Home clicked") // Stay on HomePage
                        "calendar" -> println("Calendar clicked") // Navigate to Calendar
                        "search" -> println("Search clicked") // Navigate to Search
                        "more" -> println("More clicked") // Navigate to More Options
                    }
                }
            )
        }

        // Add Task Screen
        composable("add_task") {
            val viewModel = hiltViewModel<MainViewModel>()
            AddTaskScreen(
                viewModel = viewModel,
                onSave = { navController.popBackStack() } // Navigate back to HomePage after saving
            )
        }

        // Edit Task Screen
        composable("edit_task/{taskId}") { backStackEntry ->
            val viewModel = hiltViewModel<MainViewModel>()
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()

            if (taskId != null) {
                EditTaskScreen(
                    taskId = taskId,
                    viewModel = viewModel,
                    onSave = { navController.popBackStack() } // Navigate back to HomePage after saving
                )
            } else {
                // Optional: Show an error or fallback UI if taskId is null
                println("Invalid Task ID")
            }
        }

        // Task Details Screen
        composable("task_details/{taskName}") { backStackEntry ->
            val taskName = backStackEntry.arguments?.getString("taskName") ?: "Unknown Task"
            TaskDetailsScreen(taskName = taskName)
        }
    }
}
