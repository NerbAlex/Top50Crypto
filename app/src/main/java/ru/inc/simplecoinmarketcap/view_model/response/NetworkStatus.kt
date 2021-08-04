package ru.inc.simplecoinmarketcap.view_model.response

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NetworkStatus {

    fun isOnline(): Observable<Boolean>
}