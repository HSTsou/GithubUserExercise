package com.hs.githubexercise.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hs.githubexercise.Constants
import com.hs.githubexercise.entity.User
import com.hs.githubexercise.repository.UserRepo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent


class UserViewModel(private val repo: UserRepo) : ViewModel(), KoinComponent {
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    val users = MutableLiveData<List<User>>()
    var isLoading = MutableLiveData<Boolean>()

    fun getUsers(): LiveData<List<User>> {
        return users
    }

    fun getAreaInfo() {
        val dis = repo.getAreaInfo().subscribeOn(Schedulers.io())
            .doOnEvent { _, _ -> onLoading() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userResponse ->
                val response: List<User> = userResponse
                users.value = response
                isLoading.postValue(false)
            }, { error ->
                Log.i(Constants.LOG_TAG, error.toString())
                isLoading.postValue(false)
            })

        disposables.add(dis)
    }

    fun onLoading() {
        isLoading.postValue(true)
    }
}