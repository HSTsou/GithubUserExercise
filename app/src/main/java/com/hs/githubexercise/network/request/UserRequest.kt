package com.hs.githubexercise.network.request

import com.hs.githubexercise.entity.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserRequest {

    @GET("/users")
    fun getUser(
        @Query("since") scope: Int
    ): Single<List<User>>
}
