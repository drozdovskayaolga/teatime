package com.example.teaceremony.application

import android.app.Application
import com.example.teaceremony.database.Repository
import com.example.teaceremony.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { Repository(database) }
}