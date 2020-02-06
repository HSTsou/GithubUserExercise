package com.hs.githubexercise.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hs.githubexercise.Constants

import com.hs.githubexercise.R
import com.hs.githubexercise.databinding.FragmentUserBinding
import com.hs.githubexercise.entity.User
import com.hs.githubexercise.viewModel.UserViewModel


import org.koin.android.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {
    private val userViewModel: UserViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserListAdapter

    lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(
            R.layout.fragment_user, container,
            false
        )

        recyclerView = view.findViewById(R.id.area_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter

        binding = FragmentUserBinding.bind(view)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel.getUsers().observe(this, Observer<PagedList<User>> { users ->
            adapter.submitList(users)

        })
        userViewModel.isLoading.observe(this, Observer<Boolean> { isLoading ->
            Log.i(Constants.LOG_TAG, "users observe isLoading $isLoading")
            binding.showLoading = isLoading
        })
        userViewModel.getUser()
    }
}

