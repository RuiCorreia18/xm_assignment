package com.example.xmassignment.di

import android.app.Application
import com.example.xmassignment.MainApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        AppBindModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}