package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User

class UserRepository(
    private val apiService: ApiService
) {
    fun getUsers(): List<User> {
        // Retourne la liste issue de l'apiService
        return apiService.getUsers()
    }

    fun addRandomUser() {
        // Ajoute un utilisateur aléatoire via l'apiService
        apiService.addRandomUser()
    }

    fun deleteUser(username: User) {
        // Supprime l'utilisateur via l'apiService
        apiService.deleteUser(username)
    }
}
