package com.hs.githubexercise.repository

import android.annotation.SuppressLint

import com.hs.githubexercise.entity.User
import com.hs.githubexercise.network.request.UserRequest
import com.hs.githubexercise.network.response.UserResponse

import io.reactivex.Single

interface UserRepo {

    interface LoadAreaCallback {
        fun onSuccess(userList: List<User>)

        fun onError(error: Throwable)
    }

    fun getAreaInfo(): Single<List<User>>
}

class UserRepoImpl(private val api: UserRequest) : UserRepo {
    @SuppressLint("CheckResult")
    override fun getAreaInfo(): Single<List<User>> {
        return api
            .getUser(0)
    }
}