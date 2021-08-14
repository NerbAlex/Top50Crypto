package ru.inc.simplecoinmarketcap.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.inc.simplecoinmarketcap.MyApp
import ru.inc.simplecoinmarketcap.view_model.response.CryptoRepository
import ru.inc.simplecoinmarketcap.view_model.response.NetworkStatus
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var repository: CryptoRepository

    @Inject
    lateinit var networkStatus: NetworkStatus

    private val mutableLivedata = MutableLiveData<MainViewState>()
    val liveData: LiveData<MainViewState> = mutableLivedata
    private val disposable = CompositeDisposable()

    /**
     * проверяем если есть интернет[networkStatus], получаем данные с Api, кешируем в Room и передаем в [liveData],
     * если интернет соединение отсутствует, сообщаем об этом [liveData] и идем за кешем [repository].
     */
    fun startActivity() {
        MyApp.instance.appComponent.inject(this)

        disposable.add(networkStatus.isOnline().subscribe { isOnline ->
            if (isOnline) {
                getNewCryptoList()
            } else {
                mutableLivedata.postValue(MainViewState.NoNetwork)
                getCache()
            }
        })
    }

    /**
     * Поиск крипты по буквам в полном имени и сокращении
     */
    fun searchCryptoItem(name: String) {
        disposable.clear() //TODO избавиться от подобного решения (перенести Observable с фильтрами из ui сюда?)
        disposable.add(repository.search(name).subscribeOn(Schedulers.computation()).subscribe({
            mutableLivedata.postValue(MainViewState.Success(it))
        }, {}))

    }

    /**
     * Получаем список из сети или ошибку от Api и передаем в [liveData].
     */
    private fun getNewCryptoList() {
        disposable.add(repository.getNewCryptoList().subscribe({
            mutableLivedata.postValue(MainViewState.Success(it))
        }, {
            it.printStackTrace()
            mutableLivedata.postValue(MainViewState.ApiError)
        }))
    }

    /**
     * Получаем кеш если отсустсвует сеть и передаем инф. в [liveData]
     * пустой кеш говорит о том, что это первый запуск приложения и в данный момент нет сети.
     */
    private fun getCache() {
        disposable.add(repository.getCache().subscribe({
            if (it.isNotEmpty()) {
                mutableLivedata.postValue(MainViewState.Success(it))
            } else {
                mutableLivedata.postValue(MainViewState.FirstStartNoNetworkNoCache)
            }
        }, { }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}