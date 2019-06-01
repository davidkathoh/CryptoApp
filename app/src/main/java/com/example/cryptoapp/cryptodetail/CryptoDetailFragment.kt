package com.example.cryptoapp.cryptodetail


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

import com.example.cryptoapp.R
import com.example.cryptoapp.repo.Crypto


class CryptoDetailFragment : Fragment() {

    private lateinit var bigPrice:TextView
    private lateinit var symbol:TextView
    private lateinit var price:TextView
    private lateinit var change1:TextView
    private lateinit var change7:TextView
    private lateinit var change24:TextView
    private lateinit var update:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crypto_detail, container, false)

        val cryptoName = CryptoDetailFragmentArgs.fromBundle(arguments!!).selectedCryptoName

        val viewModelFactory = CryptoDetailViewModel.CryptoDetailViewModelFactory(cryptoName)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(CryptoDetailViewModel::class.java)

        inits(view)
        bind(viewModel.crypto)

        return view
    }

    private  fun inits(view: View){
        bigPrice = view.findViewById(R.id.cr_big_price)
        price = view.findViewById(R.id.cr_price)
        symbol = view.findViewById(R.id.cr_symbol)
        change1 = view.findViewById(R.id.cr_change1)
        change7 = view.findViewById(R.id.cr_change7)
        change24 = view.findViewById(R.id.cr_change24)
        update = view.findViewById(R.id.cr_update)

    }


    private fun bind(crypto: Crypto?){
        crypto?.let {
            bigPrice.text = getString(R.string.price_frmt,crypto.price_usd)
            price.text =  getString(R.string.price_frmt,crypto.price_usd)
            symbol.text =crypto.symbol
            change24.text = crypto.percent_change_24h
            change7.text = crypto.percent_change_7d
            change1.text = crypto.percent_change_1h


            change1.setTextColor(if (crypto.percent_change_1h.contains("-"))
                Color.parseColor(getString(R.string.redText))
            else
                Color.parseColor(getString(R.string.greenText)))

            change7.setTextColor(if (crypto.percent_change_7d.contains("-"))
                Color.parseColor(getString(R.string.redText))
            else
                Color.parseColor(getString(R.string.greenText)))

            change24.setTextColor(if (crypto.percent_change_24h.contains("-"))
                Color.parseColor(getString(R.string.redText))
            else
                Color.parseColor(getString(R.string.greenText)))




        }
    }


}
