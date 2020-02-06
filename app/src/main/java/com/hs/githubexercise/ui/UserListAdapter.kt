package com.hs.githubexercise.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hs.githubexercise.R
import com.hs.githubexercise.entity.User


class UserListAdapter : PagedListAdapter<User, UserViewHolder>(UserDiffUtils) {

    companion object UserDiffUtils : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user: User = getItem(position) ?: return
        holder.bind(user!!)
    }
}

class UserViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    private var titleView: TextView? = null
    private var imageView: ImageView? = null

    init {
        titleView = view.findViewById(R.id.list_name)
        imageView = view.findViewById(R.id.pic)

    }

    fun bind(user: User) {
        titleView?.text = user.login

        imageView?.let {
            Glide.with(itemView)
                .load(user.avatarUrl)
                .circleCrop()
                .into(it)
        }
    }

}