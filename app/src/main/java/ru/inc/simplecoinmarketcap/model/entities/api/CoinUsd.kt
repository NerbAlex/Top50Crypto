package ru.inc.simplecoinmarketcap.model.entities.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CoinUsd(
    @SerializedName("PRICE")
    @Expose
    val price: String = "",

    @SerializedName("CHANGEDAY")
    @Expose
    val changeDay: String = "",

    @SerializedName("IMAGEURL")
    @Expose
    val imgUrl: String = ""
)
