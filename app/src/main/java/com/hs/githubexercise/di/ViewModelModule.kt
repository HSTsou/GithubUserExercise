package com.hs.githubexercise.di


import com.hs.githubexercise.viewModel.ProfileViewModel
import com.hs.githubexercise.viewModel.UserViewModel
import org.koin.dsl.module


val viewModelModule = module {
    factory {
        UserViewModel(get())
    }

    factory {
        ProfileViewModel(get())
    }
}