package com.shakatreh.hamditechnicalchallenge.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shakatreh.hamditechnicalchallenge.models.User

@Dao
interface UsersDoa {
    @Insert()
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE email =  :email AND password = :password")
    fun getUser(email: String, password: String): List<User>

}