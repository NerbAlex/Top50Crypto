package ru.inc.simplecoinmarketcap.di.module

import dagger.Module
import dagger.Provides
import ru.inc.simplecoinmarketcap.MyApp
import ru.inc.simplecoinmarketcap.model.data.network_status.NetworkStatusImpl
import ru.inc.simplecoinmarketcap.view_model.response.NetworkStatus
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun networkStatusProvide(app: MyApp): NetworkStatus = NetworkStatusImpl(app)
}