package ru.inc.simplecoinmarketcap.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.simplecoinmarketcap.model.entities.room.CryptoEntity

@Dao
interface CryptoDao {

    @Query("SELECT * FROM CryptoEntity")
    fun getAll(): List<CryptoEntity>

    @Insert(entity = CryptoEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(cryptoList: List<CryptoEntity>)
}