package ru.inc.simplecoinmarketcap

import android.app.Application
import ru.inc.simplecoinmarketcap.di.component.AppComponent
import ru.inc.simplecoinmarketcap.di.component.DaggerAppComponent
import ru.inc.simplecoinmarketcap.di.module.AppModule

class MyApp: Application() {

    companion object {
        @get: Synchronized
        lateinit var instance: MyApp
            private set
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}