package com.hs.githubexercise.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hs.githubexercise.R
import com.hs.githubexercise.databinding.FragmentProfileBinding
import com.hs.githubexercise.entity.User
import com.hs.githubexercise.viewModel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModel()
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(
            R.layout.fragment_profile, container,
            false
        )

        binding = FragmentProfileBinding.bind(view)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel.getUser().observe(this, Observer<User> { user ->
            profile_avatar?.let {
                Glide.with(this)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(it)
            }
            profile_name.text = user.login
            profile_sub_name.text = user.name
            profile_location.text = user.location
            profile_email.text = user.htmlUrl
            profile_email.setOnClickListener { openWeb(profile_email.text.toString()) }

        })
        profileViewModel.isLoading.observe(this, Observer<Boolean> { isLoading ->
            binding.showLoading = isLoading
        })
        profileViewModel.getProfileUser("HsTsou")
    }

    fun openWeb(url: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}