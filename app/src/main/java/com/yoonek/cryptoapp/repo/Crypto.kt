package com.yoonek.cryptoapp.repo

import com.squareup.moshi.JsonClass
import io.realm.RealmObject

@JsonClass(generateAdapter = true)
open class Crypto(
     var id: String,
     var name: String,
     var symbol:String,
     var percent_change_1h:String,
     var percent_change_24h: String,
     var percent_change_7d: String,
     var price_usd:String,
     var last_updated: String):RealmObject(){

     constructor() : this("","","","",""
          ,"","","")
}

