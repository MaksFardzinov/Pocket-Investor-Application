package com.example.pocketinvestor.pojo.user

import com.google.gson.annotations.SerializedName

data class UserStockList(@SerializedName("name") var username:String,
                         @SerializedName("price")var price:Double,
                         @SerializedName("count") var count: Int)
