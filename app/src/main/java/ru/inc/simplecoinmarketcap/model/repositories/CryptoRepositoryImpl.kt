package ru.inc.simplecoinmarketcap.model.repositories

import io.reactivex.rxjava3.core.Single
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto
import ru.inc.simplecoinmarketcap.view_model.response.CryptoRepository

class CryptoRepositoryImpl(private val cache: LocalDataSource, private val remoteDataSource: RemoteDataSource) :
    CryptoRepository {


    private var mainList: List<Crypto> = listOf()
    private var searchList: MutableList<Crypto> = mutableListOf()

    override fun getNewCryptoList(): Single<List<Crypto>> = remoteDataSource.getTop50Crypto().flatMap {
        mainList = sortHighToLow(it.toMutableList())
        cache.putAllCrypto(mainList).toSingleDefault(mainList)
    }

    override fun getCache(): Single<List<Crypto>> = cache.getAllCrypto().flatMap {
        mainList = sortHighToLow(it.toMutableList())
        Single.just(mainList)
    }

    /**
     * Если строка поиска пуста, присылаем [mainList]
     * Если есть символы, сравниваем и добавляем в [searchList]
     */
    override fun search(name: String): Single<List<Crypto>> = Single.fromCallable {
        if (name == "") {
            mainList
        } else {
            searchList.clear()
            mainList.forEach {
                if (it.name.contains(name, true) || it.fullName.contains(name, true)) {
                    searchList.add(it)
                }
            }
            searchList
        }
    }

    private fun sortHighToLow(list: MutableList<Crypto>): List<Crypto> {
        list.sortByDescending {
            it.price.drop(2).replace(",", "").toFloat()
        }
        return list
    }
}