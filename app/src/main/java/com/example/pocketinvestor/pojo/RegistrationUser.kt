package com.example.pocketinvestor.pojo

import com.google.gson.annotations.SerializedName

data class RegistrationUser(@SerializedName("username") var username:String,
                            @SerializedName("firstname")var firstname:String,
                            @SerializedName("lastname")var lastname:String,
                            @SerializedName("email")var email:String,
                            @SerializedName("password")var password:String,
                            @SerializedName("confirm_password")var confirm_password:String)

