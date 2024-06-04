package com.example.xmassignment

import android.app.Application
import com.example.xmassignment.di.AppComponent
import com.example.xmassignment.di.DaggerAppComponent

class MainApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

}