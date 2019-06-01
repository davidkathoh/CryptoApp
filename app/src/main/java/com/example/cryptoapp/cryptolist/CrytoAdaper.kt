package com.example.cryptoapp.cryptolist


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.cryptoapp.R
import com.example.cryptoapp.repo.Crypto

class CrytoAdaper( private val clickListener:CryptoListener): RecyclerView.Adapter<CrytoAdaper.CryptoViewHolder>() {

    private lateinit var crptos :List<Crypto>

    fun setCryptoList( crypos:List<Crypto>){
        crptos = crypos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {

        return CryptoViewHolder.from(parent)
    }

    override fun getItemCount() =crptos.size


    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val item = crptos[position]
        holder.bind(item,clickListener)

    }

    class CryptoViewHolder private constructor(itemView :View): RecyclerView.ViewHolder(itemView) {

        val name:TextView = itemView.findViewById(R.id.tv_name)
        private val icon:ImageView = itemView.findViewById(R.id.crypto_icon)
        val symbol:TextView = itemView.findViewById(R.id.crytoSymbol)
        val price:TextView = itemView.findViewById(R.id.crypto_price)
        //val view = view

        fun bind(item: Crypto, clickListener: CryptoListener){
            name.text = item.name
            symbol.text = item.symbol
            price.text = "$ ${item.price_usd.toFloat()}"
            Picasso
                .get()
                .load(StringBuilder("https://res.cloudinary.com/dxi90ksom/image/upload/")
                    .append(item.symbol.toLowerCase())
                    .append(".png").toString())
                .into(icon)

            itemView.setOnClickListener { clickListener.onClick(item.name)  }

        }

        companion object {
            fun from(parent: ViewGroup):CryptoViewHolder{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.crypto_item,parent,false)
                return CryptoViewHolder(view)
            }
        }
    }

    class CryptoListener(val listener:(item: String) -> Unit){
        fun onClick(str:String) = listener(str)
    }
}