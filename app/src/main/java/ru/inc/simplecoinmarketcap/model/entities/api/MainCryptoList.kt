package ru.inc.simplecoinmarketcap.model.entities.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainCryptoList(
    @SerializedName("Data")
    @Expose
    val cryptoList: List<CombineData> = ArrayList()
) {

    data class CombineData(
        @SerializedName("CoinInfo")
        @Expose
        val coinInfo: CoinInfo?,

        @SerializedName("DISPLAY")
        @Expose
        val coinDisplay: DisplayContainer?
    )
}

