package com.example.pocketinvestor.retrofit

import com.example.pocketinvestor.models.User
import com.example.pocketinvestor.pojo.JwtRequest
import com.example.pocketinvestor.pojo.RegistrationUser
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body

interface UserApi {
    @POST("/auth")
    fun auth(@Body jwtRequest: JwtRequest):Call<JwtRequest>
    @POST("/reg")
    fun reg(@Body  registrationUser: RegistrationUser):Call<RegistrationUser>
}