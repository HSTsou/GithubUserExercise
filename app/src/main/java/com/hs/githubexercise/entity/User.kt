package com.hs.githubexercise.entity


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") var login: String,
    @SerializedName("id") var id: Int,
    @SerializedName("avatar_url") var avatarUrl: String
)