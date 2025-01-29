package com.openclassrooms.magicgithub.api

import com.openclassrooms.magicgithub.model.User

class FakeApiService : ApiService {

    // On stocke la liste initiale (mutable) pour pouvoir ajouter/supprimer
    private val _users = FakeApiServiceGenerator.FAKE_USERS

    /**
     * Return a list of [User]
     * Those users must be generated by [FakeApiServiceGenerator]
     */
    override fun getUsers(): List<User> {
        // On renvoie simplement la liste en lecture seule
        return _users.toList()
    }

    /**
     * Generate a random [User] and add it to [_users].
     * This user must be retrieved from the [FakeApiServiceGenerator.FAKE_USERS_RANDOM] list.
     */
    override fun addRandomUser() {
        val randomUser = FakeApiServiceGenerator.FAKE_USERS_RANDOM.random()
        _users.add(randomUser)
    }

    /**
     * Delete a [User] from the [_users] list.
     */
    override fun deleteUser(username: User) {
        _users.remove(username)
    }
}
