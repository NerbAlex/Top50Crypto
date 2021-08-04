package ru.inc.simplecoinmarketcap.model.data.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.inc.simplecoinmarketcap.model.database.DataBase
import ru.inc.simplecoinmarketcap.model.entities.room.CryptoEntity
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto
import ru.inc.simplecoinmarketcap.model.repositories.LocalDataSource

class CryptoCache(val db: DataBase) : LocalDataSource {

    override fun getAllCrypto() = Single.fromCallable {
        db.cryptoDao.getAll().map { cacheCrypto ->
            Crypto(
                id = cacheCrypto.id,
                name = cacheCrypto.name,
                fullName = cacheCrypto.fullName,
                price = cacheCrypto.price,
                changeDay = cacheCrypto.changeDay,
                imgUrl = cacheCrypto.imgUrl
            )
        }
    }.subscribeOn(Schedulers.io())


    override fun putAllCrypto(cryptoList: List<Crypto>) = Completable.fromAction {
        val updateList = cryptoList.map { uiCrypto ->
            CryptoEntity(
                id = uiCrypto.id,
                name = uiCrypto.name,
                fullName = uiCrypto.fullName,
                price = uiCrypto.price,
                changeDay = uiCrypto.changeDay,
                imgUrl = uiCrypto.imgUrl
            )
        }
        db.cryptoDao.saveAll(updateList)
    }
}
