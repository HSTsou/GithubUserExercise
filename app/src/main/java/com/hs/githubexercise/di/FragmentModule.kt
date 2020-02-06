package com.hs.githubexercise.di

import com.hs.githubexercise.ui.ProfileFragment
import com.hs.githubexercise.ui.UserFragment
import org.koin.dsl.module

val fragmentModule = module {
    factory { UserFragment() }
    factory { ProfileFragment() }
}
