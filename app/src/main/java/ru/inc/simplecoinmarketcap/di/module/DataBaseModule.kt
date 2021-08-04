package ru.inc.simplecoinmarketcap.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.inc.simplecoinmarketcap.MyApp
import ru.inc.simplecoinmarketcap.model.data.room.CryptoCache
import ru.inc.simplecoinmarketcap.model.repositories.LocalDataSource
import ru.inc.simplecoinmarketcap.model.database.DataBase
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun dataBaseProvide(app: MyApp) = Room.databaseBuilder(app, DataBase::class.java, DataBase.DB_NAME).build()

    @Singleton
    @Provides
    fun cryptoCacheProvide(db: DataBase): LocalDataSource = CryptoCache(db)
}