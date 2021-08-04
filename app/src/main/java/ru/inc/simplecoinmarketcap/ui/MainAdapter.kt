package ru.inc.simplecoinmarketcap.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.inc.simplecoinmarketcap.MyApp
import ru.inc.simplecoinmarketcap.databinding.ItemCryptoBinding
import ru.inc.simplecoinmarketcap.extensions.glide
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    var list: List<Crypto> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            ItemCryptoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size


    class MyViewHolder(val ui: ItemCryptoBinding) : RecyclerView.ViewHolder(ui.root) {

        val base_url = "https://www.cryptocompare.com"

        fun bind(crypto: Crypto) {
//            ui.iconCrypto.glide(crypto.imgUrl, ui.iconCrypto)
            ui.nameCrypto.text = crypto.name
            ui.nameCryptoFull.text = crypto.fullName
            ui.nameCryptoPrice.text = crypto.price
            ui.nameCryptoDayChange.text = crypto.changeDay

            Glide.with(MyApp.instance.applicationContext)
//                .asBitmap()
//                .centerCrop()
                .load("$base_url${crypto.imgUrl}")
                .into(ui.iconCrypto)
        }
    }
}