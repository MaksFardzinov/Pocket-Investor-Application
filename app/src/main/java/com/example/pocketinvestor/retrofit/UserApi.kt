package com.example.pocketinvestor.retrofit

import com.example.pocketinvestor.pojo.user.UserStockList
import com.example.pocketinvestor.pojo.user.RegistrationUser
import com.example.pocketinvestor.pojo.stoks.StockList
import com.example.pocketinvestor.pojo.jwt.JwtRequest
import com.example.pocketinvestor.pojo.jwt.JwtResponse
import com.example.pocketinvestor.pojo.stoks.StockForBuyRequest
import com.example.pocketinvestor.pojo.user.UserInfoResponse
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @POST("/auth")
    fun auth(@Body jwtRequest: JwtRequest):Call<JwtResponse>
    @POST("/reg")
    fun reg(@Body registrationUser: RegistrationUser):Call<RegistrationUser>
    @GET("/info")
    fun  info(@Header("Authorization") token:String):Call<UserInfoResponse>
    @GET("/getUserActions")
    fun  getUserStocks(@Header("Authorization") token:String): Call<List<UserStockList>>
    @GET("/getActions")
    fun  getStocks(): Call<List<StockList>>
    @POST("/addAction")
    fun buyAction(@Header("Authorization")token: String, @Body stockForBuyRequest: StockForBuyRequest): Call<String>
    @POST ("/replenishBalance")
    fun replenishBalance(@Header("Authorization")token: String, @Body money:Double): Call<String>
    @POST("/sellAction")
    fun sellAction (@Header("Authorization")token: String, @Body stockForBuyRequest: StockForBuyRequest): Call<String>

    @POST("/updatePrice")
    fun updatePrice():Call<String>
}