package com.mehmetgenc.secondhand.user.dto

data class CreateUserRequest(
        val mail: String?,
        val firstName: String?,
        val middleName: String?,
        val lastName: String?
)
