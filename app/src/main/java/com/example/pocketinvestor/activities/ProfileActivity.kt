package com.example.pocketinvestor.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.pocketinvestor.R
import com.example.pocketinvestor.pojo.user.UserInfoResponse
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        install()
    }
    private fun install() {
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)
        val username: TextView = findViewById(R.id.username)
        val balance: TextView = findViewById(R.id.balance)
        val email: TextView = findViewById(R.id.email)
        val token = intent.getStringExtra("token")
        var balanceValue =""
        val buttonPortfolio: MaterialButton = findViewById(R.id.go_to_portfolio)
        val buttonStocks: MaterialButton = findViewById(R.id.go_to_stocks)
        val buttonBalance: MaterialButton = findViewById(R.id.replenishBalance)

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
                        val usernameValue: String = response.body()!!.username
                        val emailValue: String = response.body()!!.email
                        balanceValue = response.body()!!.balance.toString()
                        username.text = "username: $usernameValue"
                        email.text = "email: $emailValue"
                        balance.text = "balance: $balanceValue"
                    }
                })
        }
        buttonPortfolio.setOnClickListener {
            userApi.updatePrice().enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                }

            })
            val intent = Intent(this@ProfileActivity, PortfolioActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        buttonStocks.setOnClickListener {
            userApi.updatePrice().enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                }

            })
            val intent = Intent(this@ProfileActivity, StocksActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        buttonBalance.setOnClickListener {
            val intent = Intent(this@ProfileActivity, ReplenishBalanceActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }

    }
    }