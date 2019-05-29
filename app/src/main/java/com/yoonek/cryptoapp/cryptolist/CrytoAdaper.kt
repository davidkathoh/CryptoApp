package com.yoonek.cryptoapp.cryptolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.yoonek.cryptoapp.R
import com.yoonek.cryptoapp.database.Crypto

class CrytoAdaper(val clickListener:CryptoListener,list: List<Crypto>): RecyclerView.Adapter<CrytoAdaper.CryptoViewHolder>() {

    val arr = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {

        return CryptoViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
       return arr.size
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val item = arr[position]
        holder.bind(item,clickListener)

    }


    class CryptoViewHolder private constructor(itemView :View): RecyclerView.ViewHolder(itemView) {

        val name:TextView = itemView.findViewById(R.id.tv_name)
        val icon:ImageView = itemView.findViewById(R.id.crypto_icon)
        val date:TextView = itemView.findViewById(R.id.crytoUpdateDate)
        //val view = view

        fun bind(item: Crypto, clickListener: CryptoListener){
            name.text = item.name
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