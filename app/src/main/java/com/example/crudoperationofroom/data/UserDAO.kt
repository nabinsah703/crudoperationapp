package com.example.crudoperationofroom.data

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crudoperationofroom.model.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun DeleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>
}