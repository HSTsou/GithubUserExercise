package com.hs.githubexercise.repository

import android.annotation.SuppressLint
import com.hs.githubexercise.entity.User
import com.hs.githubexercise.network.request.UserRequest
import io.reactivex.Single

interface ProfileRepo {
    fun getSpecificUser(userName: String): Single<User>
}

class ProfileRepoImpl(private val api: UserRequest) : ProfileRepo {
    @SuppressLint("CheckResult")
    override fun getSpecificUser(userName: String): Single<User> {
        return api.getSpecificUser(userName)
    }
}