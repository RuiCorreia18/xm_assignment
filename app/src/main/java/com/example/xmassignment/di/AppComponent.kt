package com.example.xmassignment.di

import android.app.Application
import android.content.Context
import com.example.xmassignment.MainActivity
import com.example.xmassignment.MainApplication
import com.example.xmassignment.di.viewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        AppBindModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun inject(app: MainApplication)
    fun inject(activity: MainActivity)

}