package com.example.pocketinvestor.pojo

import com.google.gson.annotations.SerializedName

data class JwtResponse(@SerializedName("token") var token:String)
