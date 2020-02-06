package com.hs.githubexercise.network.request

import com.hs.githubexercise.entity.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserRequest {

    @GET("/users")
    fun getUser(
        @Query("since") scope: Int = 0
    ): Call<List<User>>

    @GET("/users/{userName}")
    fun getSpecificUser(
        @Path("userName") userName: String
    ): Single<User>
}
