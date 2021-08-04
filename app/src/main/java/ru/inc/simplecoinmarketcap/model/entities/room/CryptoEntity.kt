package ru.inc.simplecoinmarketcap.model.entities.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class CryptoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val fullName: String,
    val price: String,
    val changeDay: String,
    val imgUrl: String
)