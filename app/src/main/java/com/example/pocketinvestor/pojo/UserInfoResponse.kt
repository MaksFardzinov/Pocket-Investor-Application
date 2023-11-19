package com.example.pocketinvestor.pojo

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(@SerializedName("username") var username:String,
                            @SerializedName("email")var email:String)
