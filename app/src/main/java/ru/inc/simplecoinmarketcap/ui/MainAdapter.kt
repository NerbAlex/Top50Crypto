package ru.inc.simplecoinmarketcap.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.inc.simplecoinmarketcap.MyApp
import ru.inc.simplecoinmarketcap.databinding.ItemCryptoBinding
import ru.inc.simplecoinmarketcap.model.entities.ui.Crypto

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    companion object {
        const val BASE_URL = "https://www.cryptocompare.com"
    }


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

        fun bind(crypto: Crypto) {
            ui.nameCrypto.text = crypto.name
            ui.nameCryptoFull.text = crypto.fullName
            ui.nameCryptoPrice.text = crypto.price
            ui.nameCryptoDayChange.text = crypto.changeDay

            Glide.with(MyApp.instance.applicationContext)
                .load("$BASE_URL${crypto.imgUrl}")
                .into(ui.iconCrypto)
        }
    }
}