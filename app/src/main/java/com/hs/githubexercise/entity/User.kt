package com.hs.githubexercise.entity


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") var login: String,
    @SerializedName("id") var id: Int,
    @SerializedName("avatar_url") var avatarUrl: String,
    @SerializedName("location") var location: String,
    @SerializedName("name") var name: String,
    @SerializedName("bio") var bio: String,
    @SerializedName("html_url") var htmlUrl: String

)
