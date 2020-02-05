package com.hs.githubexercise.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hs.githubexercise.R
import com.hs.githubexercise.entity.User


class UserListAdapter : RecyclerView.Adapter<UserViewHolder>() {

    var list: List<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (list == null) {
            return
        }
        val area: User = list!![position]
        holder.bind(area)
    }

    override fun getItemCount(): Int {
        if (list == null) {
            return 0
        }
        return list!!.size
    }

    fun updateAreas(areas: List<User>) {
        list = areas
        notifyDataSetChanged()
    }
}

class UserViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    private var mTitleView: TextView? = null
    private var mImageView: ImageView? = null

    init {
        mTitleView = view.findViewById(R.id.list_name)
        mImageView = view.findViewById(R.id.pic)

    }

    fun bind(user: User) {
        mTitleView?.text = user.login

        mImageView?.let {
            Glide.with(itemView)
                .load(user.avatarUrl)
                .circleCrop()
                .into(it)
        }
    }

}