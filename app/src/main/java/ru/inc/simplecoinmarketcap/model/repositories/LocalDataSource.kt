package ru.inc.simplecoinmarketcap.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto

interface LocalDataSource {

    fun getAllCrypto(): Single<List<Crypto>>
    fun putAllCrypto(cryptoList: List<Crypto>): Completable
}