package ru.inc.simplecoinmarketcap.model.repositories

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.inc.simplecoinmarketcap.model.entities.api.MainCryptoList
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto

interface RemoteDataSource {

    fun getTop50Crypto(): Single<List<Crypto>>

}