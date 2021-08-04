package ru.inc.simplecoinmarketcap.view_model.response

import io.reactivex.rxjava3.core.Single
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto

interface CryptoRepository {

    fun getNewCryptoList(): Single<List<Crypto>>
    fun getCache(): Single<List<Crypto>>
    fun search(name: String): Single<List<Crypto>>
}