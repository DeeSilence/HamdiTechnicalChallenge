package com.shakatreh.hamditechnicalchallenge.di

import com.shakatreh.hamditechnicalchallenge.db.UsersDoa
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val usersDoa: UsersDoa) {
}