package ru.inc.simplecoinmarketcap.model.data.network_status

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.inc.simplecoinmarketcap.view_model.response.NetworkStatus

class NetworkStatusImpl(context: Context) : NetworkStatus {

    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {

        statusSubject.onNext(false)

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()

        connectivityManager.registerNetworkCallback(request,
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    statusSubject.onNext(true)
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                }
            })
    }

    override fun isOnline() = statusSubject.subscribeOn(Schedulers.single())
}