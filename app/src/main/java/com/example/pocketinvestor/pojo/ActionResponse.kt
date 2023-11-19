package com.example.pocketinvestor.pojo

import com.google.gson.annotations.SerializedName

data class ActionResponse(@SerializedName("username") var username:String,
                          @SerializedName("price")var price:Double)
