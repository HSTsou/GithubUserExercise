package com.hs.githubexercise.di;


import com.hs.githubexercise.repository.UserRepo
import com.hs.githubexercise.repository.UserRepoImpl
import org.koin.dsl.module

val repoModule = module {
    single<UserRepo> {
        UserRepoImpl(
            get()
        )
    }
}