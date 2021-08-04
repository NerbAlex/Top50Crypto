package ru.inc.simplecoinmarketcap.view_model

import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto

sealed class MainViewState {
    data class Success(val cryptoList: List<Crypto>): MainViewState()
    object NoNetwork: MainViewState()
    object FirstStartNoNetworkNoCache: MainViewState()
    object ApiError: MainViewState()
}
