package com.openclassrooms.magicgithub.api

import com.openclassrooms.magicgithub.model.User

interface ApiService {
    fun getUsers(): List<User>
    fun addRandomUser()
    fun deleteUser(username: User)
    fun swipeUser(fromPosition: Int, toPosition: Int)
    fun active(user: User)
}