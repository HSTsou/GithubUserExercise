package com.hs.githubexercise.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.hs.githubexercise.Constants
import com.hs.githubexercise.entity.User
import com.hs.githubexercise.network.request.UserRequest
import io.reactivex.Observable

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

interface UserRepo {
    fun getUsers(): Observable<PagedList<User>>
}

class UserRepoImpl(private val api: UserRequest) : UserRepo {
    @SuppressLint("CheckResult")
    override fun getUsers(): Observable<PagedList<User>> {
        Log.i(Constants.LOG_TAG, "getUsers ")
//        return api
//            .getUser(0)
        val dataSource = UserPagingDataSourceFactory()
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(9)
            .setPrefetchDistance(4)
            .build()


        return RxPagedListBuilder(dataSource, pagedListConfig)
            .setFetchScheduler(Schedulers.io())
            .setNotifyScheduler(AndroidSchedulers.mainThread())
            .buildObservable()
    }
}