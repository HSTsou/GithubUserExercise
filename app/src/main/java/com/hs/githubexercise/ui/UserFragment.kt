package com.hs.githubexercise.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hs.githubexercise.Constants

import com.hs.githubexercise.R
import com.hs.githubexercise.entity.User
import com.hs.githubexercise.viewModel.UserViewModel


import org.koin.android.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {
    private val areaViewModel: UserViewModel by viewModel()

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserListAdapter

//    lateinit var binding: FragmentAreaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        binding = FragmentAreaBinding.inflate(inflater)

        val view: View = inflater.inflate(
            R.layout.fragment_area, container,
            false
        )

        recyclerView = view.findViewById(R.id.area_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        areaViewModel.getUsers().observe(this, Observer<List<User>> { areas ->
            Log.i(Constants.LOG_TAG, "areas observe $areas")
            adapter.updateAreas(areas)
        })
//        areaViewModel.isLoading.observe(this, Observer<Boolean> { isLoading ->
//            binding.showLoading = isLoading
//        })
        areaViewModel.getAreaInfo()
    }
}

