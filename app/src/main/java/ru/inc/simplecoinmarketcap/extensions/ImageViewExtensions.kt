package ru.inc.simplecoinmarketcap.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

const val base_url = "https://www.cryptocompare.com/media/37746339/doge.png"

fun ImageView.glide(url: String, iconCrypto: ImageView) =
    Glide.with(iconCrypto.context)
        .asBitmap()
        .load("$base_url")
        .into(iconCrypto)