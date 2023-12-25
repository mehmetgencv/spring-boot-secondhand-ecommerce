package com.mehmetgenc.secondhand.user.dto

data class UserDto(

        val mail: String?,
        val firstName: String?,
        val middleName: String?,
        val lastName: String?,
        val userDetails: List<UserDetailsDto>
)
