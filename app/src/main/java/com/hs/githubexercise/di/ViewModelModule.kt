package com.hs.githubexercise.di;


import com.hs.githubexercise.viewModel.UserViewModel
import org.koin.dsl.module


val viewModelModule = module {
    factory {
        //        val db = AppDatabase.getDatabase(get()) // or androidContext()
        UserViewModel(get())
    }

    factory {
//        FavAreaViewModel()
    }
}