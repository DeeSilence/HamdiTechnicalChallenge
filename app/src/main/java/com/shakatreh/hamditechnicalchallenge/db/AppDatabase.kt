package com.shakatreh.hamditechnicalchallenge.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shakatreh.hamditechnicalchallenge.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDoa(): UsersDoa
}