package com.nonmagis.startwarsapp

import android.app.Application
import com.nonmagis.startwarsapp.di.repoModule
import com.nonmagis.startwarsapp.di.retrofitModule
import com.nonmagis.startwarsapp.di.roomModule
//import com.nonmagis.startwarsapp.di.roomModule
import com.nonmagis.startwarsapp.di.uiModule
import com.nonmagis.startwarsapp.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(retrofitModule, repoModule, useCasesModule, uiModule, roomModule)
        }
    }
}