package com.hs.githubexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hs.githubexercise.ui.UserFragment

import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val userFragment: UserFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigation()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, userFragment)
                .commit()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_area_list -> {
                    val frag = userFragment
                    replaceFragment(frag, "AreaListFragment")

                }
                R.id.action_favorites -> {
                    val frag = userFragment
                    replaceFragment(frag, "FavAreaFragment")

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag).addToBackStack("").commit()
    }
}
