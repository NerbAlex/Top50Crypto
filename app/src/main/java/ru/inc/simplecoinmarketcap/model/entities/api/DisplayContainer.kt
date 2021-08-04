package ru.inc.simplecoinmarketcap.model.entities.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DisplayContainer(
    @SerializedName("USD")
    @Expose
    val usd: CoinUsd
)
