package ru.inc.simplecoinmarketcap.model.entities.ui

data class Crypto(
    val id: Long,
    val name: String,
    val fullName: String,
    var price: String,
    val changeDay: String,
    val imgUrl: String
)
