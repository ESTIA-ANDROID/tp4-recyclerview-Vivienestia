package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.utils.UserDiffCallback
import kotlin.collections.ArrayList

class UserListAdapter(
    private val callback: Listener
) : RecyclerView.Adapter<ListUserViewHolder>() {

    interface Listener {
        fun onClickDelete(user: User)
    }

    // -- Liste mutable (pour pouvoir échanger facilement) --
    private var users: List<User> = ArrayList()

    // -- On "gonfle" le layout via ViewBinding --
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val binding =
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }

    // -- Binder un item --
    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user, callback)
    }

    override fun getItemCount(): Int = users.size

    // -- Méthode pour récupérer un item (swipe) --
    fun getItem(position: Int): User = users[position]

    // -- Méthode pour remplacer toute la liste --
    fun updateList(newList: List<User>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(newList,users))
        users = newList.toList()
        diffResult.dispatchUpdatesTo(this)
    }
}
