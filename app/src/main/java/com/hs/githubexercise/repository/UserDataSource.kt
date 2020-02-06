package com.hs.githubexercise.repository

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hs.githubexercise.Constants
import com.hs.githubexercise.entity.User
import com.hs.githubexercise.network.request.UserRequest
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.KoinComponent
import org.koin.core.inject


class UserDataSource : PageKeyedDataSource<String, User>(), KoinComponent {
    private val api: UserRequest by inject()


    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, User>
    ) {

        val response = api.getUser().execute()
        if (response.isSuccessful) {
            val nextPageUrl = parseNextPageUrl(response.headers())
            val userList = response.body()
            callback.onResult(userList as MutableList<User>, null, nextPageUrl)
        } else {
            Log.i(Constants.LOG_TAG, "loadInitial error ")
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        val url = params.key
        Log.i(Constants.LOG_TAG, "loadAfter $url")
        if (url.isNotEmpty()) {
            val response = OkHttpClient().newCall(Request.Builder().url(url).build()).execute()
            if (response.isSuccessful) {
                val nextPageUrl = parseNextPageUrl(response.headers)
                val listType = object : TypeToken<List<User>>() {}.type
                val userList: List<User> = Gson().fromJson(
                    response.body?.string(),
                    listType
                )
                callback.onResult(userList, nextPageUrl)
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {

    }

    private fun parseNextPageUrl(headers: Headers): String? {
        val link: String = headers.get("Link") ?: return null
        val result = link.split(";")
        return result[0].replace("<", "").replace(">", "")
    }

}

class UserPagingDataSourceFactory : DataSource.Factory<String, User>() {
    override fun create(): DataSource<String, User> {
        return UserDataSource()
    }
}