package com.hs.githubexercise.viewModel

import com.hs.githubexercise.repository.ProfileRepo


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hs.githubexercise.Constants
import com.hs.githubexercise.entity.User

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class ProfileViewModel(private val repo: ProfileRepo) : ViewModel(), KoinComponent {
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    val user = MutableLiveData<User>()
    var isLoading = MutableLiveData<Boolean>()

    fun getUser(): LiveData<User> {
        return user
    }

    fun getProfileUser(userName: String) {
        onLoading()
        val dis = repo.getSpecificUser(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userResponse ->
                Log.i(Constants.LOG_TAG, "userResponse @VM $userResponse")
                user.value = userResponse
                isLoading.value = false
            }, { error ->
                Log.i(Constants.LOG_TAG, error.toString())
                isLoading.value = false
            })

        disposables.add(dis)
    }

    fun onLoading() {
        isLoading.postValue(true)
    }
}
