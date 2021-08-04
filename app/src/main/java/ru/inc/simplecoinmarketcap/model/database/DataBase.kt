package ru.inc.simplecoinmarketcap.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.inc.simplecoinmarketcap.model.entities.room.CryptoEntity


@Database(
    entities = [
        CryptoEntity::class
    ], version = 1
)
abstract class DataBase: RoomDatabase() {

    abstract val cryptoDao: CryptoDao

    companion object {
        const val DB_NAME = "database.db"
    }
}