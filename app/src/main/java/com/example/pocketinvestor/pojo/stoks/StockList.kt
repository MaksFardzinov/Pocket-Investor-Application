package com.example.pocketinvestor.pojo.stoks

import com.google.gson.annotations.SerializedName

data class StockList(@SerializedName("name") var username:String,
                     @SerializedName("description") var description:String,
                     @SerializedName("price")var price:Double)
