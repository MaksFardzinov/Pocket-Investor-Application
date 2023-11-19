package com.example.pocketinvestor.retrofit

import com.example.pocketinvestor.pojo.JwtRequest
import com.example.pocketinvestor.pojo.JwtResponse
import com.example.pocketinvestor.pojo.RegistrationUser
import com.example.pocketinvestor.pojo.UserInfoResponse
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserApi {
    @POST("/auth")
    fun auth(@Body jwtRequest: JwtRequest):Call<JwtResponse>
    @POST("/reg")
    fun reg(@Body registrationUser: RegistrationUser):Call<RegistrationUser>
    @GET("/info")
    fun  info(@Header("Authorization") token:String):Call<UserInfoResponse>

}