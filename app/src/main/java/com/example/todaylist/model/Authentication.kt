package com.example.todaylist.model

data class AuthenticationRequest(
    val email: String, // Email for login or registration
    val password: String // Password for login or registration
)

data class AuthenticationResponse(
    val userId: Int, // ID of the authenticated user
    val token: String // Authentication token (for API authentication)
)
