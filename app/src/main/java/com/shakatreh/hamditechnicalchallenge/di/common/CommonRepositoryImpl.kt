package com.shakatreh.hamditechnicalchallenge.di.common

import android.content.Context

class CommonRepositoryImpl(private val context: Context) : CommonRepository {
    override fun getContext(): Context {
        return context
    }

}