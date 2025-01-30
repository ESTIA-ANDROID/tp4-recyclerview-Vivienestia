package com.openclassrooms.magicgithub.ui.user_list

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User

class ListUserViewHolder(
    private val binding: ItemListUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, callback: UserListAdapter.Listener) {
        // Ex: Colorer en rouge si inactif
        val colorRes = if (user.isActive) {
            android.R.color.white
        } else {
            android.R.color.holo_red_light
        }
        binding.root.setBackgroundColor(
            ContextCompat.getColor(binding.root.context, colorRes)
        )

        // Charger l'avatar avec Glide
        Glide.with(binding.root.context)
            .load(user.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.itemListUserAvatar)

        // Nom de l'utilisateur
        binding.itemListUserUsername.text = user.login

        // Bouton delete
        binding.itemListUserDeleteButton.setOnClickListener {
            callback.onClickDelete(user)
        }
    }
}
