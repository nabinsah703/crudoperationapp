package com.example.crudoperationofroom.repository

import androidx.lifecycle.LiveData
import com.example.crudoperationofroom.data.UserDAO
import com.example.crudoperationofroom.model.User

class UserRepository(private val userDAO: UserDAO) {


    val readAllData: LiveData<List<User>> = userDAO.readAllData()

    suspend fun addUser(user: User){
        userDAO.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDAO.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDAO.DeleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDAO.deleteAllUsers()
    }
}