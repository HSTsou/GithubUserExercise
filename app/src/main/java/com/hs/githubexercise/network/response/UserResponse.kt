package com.hs.githubexercise.network.response

import com.hs.githubexercise.entity.User


data class UserResponse(
    val results: List<User>
)