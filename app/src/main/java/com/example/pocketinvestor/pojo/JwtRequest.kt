package com.example.pocketinvestor.pojo

import com.google.gson.annotations.SerializedName

data class JwtRequest (@SerializedName("username") var username:String,
                       @SerializedName("password") var password:String)