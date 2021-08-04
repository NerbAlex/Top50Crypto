package ru.inc.simplecoinmarketcap.model.data.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.inc.simplecoinmarketcap.model.entities.api.MainCryptoList

interface ApiDataSource {

    @GET("/data/top/totalvolfull?limit=50&tsym=USD")
    fun getTop50Crypto(): Single<MainCryptoList>
}