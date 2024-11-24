package com.example.todaylist

import androidx.compose.foundation.layout.* // Required for layout
import androidx.compose.material3.* // Required for Material components
import androidx.compose.runtime.* // Required for state management
import androidx.compose.ui.Alignment // Required for alignment
import androidx.compose.ui.Modifier // Required for modifiers
import androidx.compose.ui.text.input.PasswordVisualTransformation // Required for password transformation
import androidx.compose.ui.unit.dp // Required for spacing and padding

@Composable
fun SignUpScreen(onSignUpSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Validation for password mismatch
    val isPasswordMismatch = password != confirmPassword && confirmPassword.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ToDay List", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        // Display error message if passwords don't match
        if (isPasswordMismatch) {
            Text(
                text = "Passwords do not match",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (!isPasswordMismatch && email.isNotBlank() && password.isNotBlank()) {
                    onSignUpSuccess()
                }
            },
            enabled = email.isNotBlank() && password.isNotBlank() && !isPasswordMismatch,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign up")
        }
    }
}
