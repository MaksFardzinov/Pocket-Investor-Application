package com.example.pocketinvestor.pojo.user

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(@SerializedName("username") var username:String,
                            @SerializedName("email")var email:String,
                            @SerializedName("balance")var balance:Double)
