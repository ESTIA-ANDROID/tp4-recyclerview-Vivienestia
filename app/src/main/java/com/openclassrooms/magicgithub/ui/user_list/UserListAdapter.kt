package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User
import java.util.*

class UserListAdapter(
    private val callback: Listener
) : RecyclerView.Adapter<ListUserViewHolder>() {

    interface Listener {
        fun onClickDelete(user: User)
    }

    // -- Liste mutable (pour pouvoir échanger facilement) --
    private val users: MutableList<User> = mutableListOf()

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
        // On vide la liste existante, et on ajoute les nouveaux items
        users.clear()
        users.addAll(newList)
        // On notifie qu’on a rechargé la liste
        notifyDataSetChanged()
    }

    // -- Méthode pour échanger deux items (drag & drop) --
    fun swapItems(fromPosition: Int, toPosition: Int) {
        // Échange dans la liste
        Collections.swap(users, fromPosition, toPosition)
        // Notifie le RecyclerView du déplacement
        notifyItemMoved(fromPosition, toPosition)
    }
}
