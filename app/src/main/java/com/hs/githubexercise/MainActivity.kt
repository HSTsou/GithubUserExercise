package com.hs.githubexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hs.githubexercise.ui.ProfileFragment
import com.hs.githubexercise.ui.UserFragment

import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val userFragment: UserFragment by inject()
    private val profileFragment: ProfileFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigation()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, profileFragment)
                .hide(profileFragment)
                .add(R.id.fragment_container, userFragment)
                .commit()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_area_list -> {
                    supportFragmentManager.beginTransaction()
                        .hide(profileFragment)
                        .show(userFragment)
                        .commit()

                }
                R.id.action_favorites -> {
                    supportFragmentManager.beginTransaction()
                        .hide(userFragment)
                        .show(profileFragment)
                        .commit()

                }
            }
            true
        }
    }
}
