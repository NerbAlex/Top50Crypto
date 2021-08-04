package ru.inc.simplecoinmarketcap.model.data.api

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto
import ru.inc.simplecoinmarketcap.model.repositories.RemoteDataSource
import java.util.logging.Logger

class RemoteDataSourceImpl(val api: ApiDataSource) : RemoteDataSource {

    val LOG = Logger.getLogger(RemoteDataSourceImpl::class.java.name)


    override fun getTop50Crypto(): Single<List<Crypto>> = api.getTop50Crypto().flatMap { apiList ->
        Single.just(apiList.cryptoList.map { apiCrypto ->
            Crypto(
                name = apiCrypto.coinInfo?.name ?: "",
                fullName = apiCrypto.coinInfo?.fullName ?: "",
                id = apiCrypto.coinInfo?.id?.toLong() ?: 0L,
                imgUrl = apiCrypto.coinDisplay?.usd?.imgUrl ?: "",
                price = apiCrypto.coinDisplay?.usd?.price ?: "0.000001",
                changeDay = apiCrypto.coinDisplay?.usd?.changeDay ?: ""
            )
        })
    }.subscribeOn(Schedulers.io())
}