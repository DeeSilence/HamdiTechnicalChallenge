package com.shakatreh.hamditechnicalchallenge.repos

import com.shakatreh.hamditechnicalchallenge.db.AppDatabase
import com.shakatreh.hamditechnicalchallenge.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepo @Inject constructor(
    private val appDatabase: AppDatabase
) {
    suspend fun getUser(email: String, password: String): User? {
        val user = withContext(Dispatchers.IO) {
            appDatabase.usersDoa().getUser(email, password)
        }
        return if (user.isEmpty())
            null
        else
            user[0]
    }

    suspend fun insertUser(email: String, password: String, dob: Long) {
        withContext(Dispatchers.IO) {
            appDatabase.usersDoa().insertUser(User(email, password, dob))
        }
    }

}