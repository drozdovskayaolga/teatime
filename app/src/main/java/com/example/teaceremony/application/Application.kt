package com.example.teaceremony.application

import android.app.Application
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.database.AppDatabase
import com.example.teaceremony.database.Repository
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_ingredients.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class Application : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())


    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { Repository(database) }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}