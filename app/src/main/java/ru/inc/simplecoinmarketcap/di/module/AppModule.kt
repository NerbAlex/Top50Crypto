package ru.inc.simplecoinmarketcap.di.module

import dagger.Module
import dagger.Provides
import ru.inc.simplecoinmarketcap.MyApp
import javax.inject.Singleton

@Module
class AppModule(val app: MyApp) {

    @Singleton
    @Provides
    fun appProvide(): MyApp = app
}