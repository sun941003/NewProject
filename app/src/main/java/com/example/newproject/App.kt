package com.example.newproject

import android.app.Application
import com.example.newproject.di.mvvmModule
import com.example.newproject.di.serviceModule
import com.example.newproject.view.ui.base.BaseActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var instance : App
        var currentActivity: BaseActivity<*>? = null
        var activities = ArrayList<BaseActivity<*>>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@App)
            modules(listOf(mvvmModule, serviceModule))
        }
    }
}