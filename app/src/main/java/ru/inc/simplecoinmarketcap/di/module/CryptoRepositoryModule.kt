package ru.inc.simplecoinmarketcap.di.module

import dagger.Module
import dagger.Provides
import ru.inc.simplecoinmarketcap.model.repositories.RemoteDataSource
import ru.inc.simplecoinmarketcap.model.repositories.LocalDataSource
import ru.inc.simplecoinmarketcap.model.repositories.CryptoRepositoryImpl
import ru.inc.simplecoinmarketcap.view_model.response.CryptoRepository
import javax.inject.Singleton

@Module
class CryptoRepositoryModule {

    @Singleton
    @Provides
    fun cryptoRepositoryProvide(cache: LocalDataSource, remoteDataSource: RemoteDataSource): CryptoRepository =
        CryptoRepositoryImpl(cache, remoteDataSource)
}