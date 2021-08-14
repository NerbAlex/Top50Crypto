package ru.inc.simplecoinmarketcap.model.repositories

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto
import ru.inc.simplecoinmarketcap.view_model.response.CryptoRepository

class CryptoRepositoryImpl(
    private val cache: LocalDataSource, private val remoteDataSource: RemoteDataSource
) :
    CryptoRepository {

    override val searchPublishSubject: PublishSubject<List<Crypto>> = PublishSubject.create()


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
    override fun search(name: String) {
        if (name == "") {
            searchPublishSubject.onNext(mainList)
        } else {
            searchList.clear()
            mainList.forEach {
                if (it.name.contains(name, true) || it.fullName.contains(name, true)) {
                    searchList.add(it)
                }
            }
            searchPublishSubject.onNext(searchList)
        }
    }


    private fun sortHighToLow(list: MutableList<Crypto>): List<Crypto> {
        list.sortByDescending {
            it.price.drop(2).replace(",", "").toFloat()
        }
        return list
    }
}