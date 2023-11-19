package com.example.pocketinvestor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.pocketinvestor.R
import com.example.pocketinvestor.pojo.RegistrationUser
import com.example.pocketinvestor.pojo.UserInfoResponse
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        getInfo()
    }
    private fun getInfo(){
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)
        val username :TextView = findViewById(R.id.username)
        val email :TextView = findViewById(R.id.email)
        val token  = intent.getStringExtra("token")


        if (token != null) {
            userApi.info("Bearer $token")
                .enqueue(object : Callback<UserInfoResponse> {

                    override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                        Toast.makeText(this@ProfileActivity, "fail", Toast.LENGTH_LONG).show()
                        Logger.getLogger("error", t.toString())
                    }

                    override fun onResponse(
                        call: Call<UserInfoResponse>,
                        response: Response<UserInfoResponse>
                    ) {
                        Toast.makeText(this@ProfileActivity, "successful", Toast.LENGTH_LONG).show()
                        println(response.body()?.username)
                        println(response.body()?.email)
                        username.text = response.body()!!.username
                        email.text =  response.body()!!.email
                    }
                })
        }
                }
    }