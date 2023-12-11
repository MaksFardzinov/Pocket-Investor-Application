package com.example.pocketinvestor.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketinvestor.R
import com.example.pocketinvestor.pojo.user.UserInfoResponse
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class ReplenishBalanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replenish_balance)
        install()
    }
    private fun install() {
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)
        val username: TextView = findViewById(R.id.username)
        val balance: TextView = findViewById(R.id.balance)
        val buttonReplenishBalance: MaterialButton = findViewById(R.id.replenishBalance)
        val buttonPortfolio: MaterialButton = findViewById(R.id.go_to_profile)
        val inputBalance:TextInputEditText = findViewById(R.id.replenish_balance_form)
        val token = intent.getStringExtra("token")
        var balanceValue =""
        if (token != null) {
            userApi.info("Bearer $token")
                .enqueue(object : Callback<UserInfoResponse> {
                    override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                        Toast.makeText(this@ReplenishBalanceActivity, "fail", Toast.LENGTH_LONG).show()
                        Logger.getLogger("error", t.toString())
                    }

                    override fun onResponse(
                        call: Call<UserInfoResponse>,
                        response: Response<UserInfoResponse>
                    ) {
                        Toast.makeText(this@ReplenishBalanceActivity, "successful", Toast.LENGTH_LONG).show()
                        val usernameValue: String = response.body()!!.username
                        balanceValue = response.body()!!.balance.toString()
                        username.text = "username: $usernameValue"
                        balance.text = "balance: $balanceValue"
                    }
                })
        }
        buttonPortfolio.setOnClickListener {
            val intent = Intent(this@ReplenishBalanceActivity, ProfileActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        buttonReplenishBalance.setOnClickListener {
            val money = inputBalance.text.toString();
            userApi.replenishBalance("Bearer $token",money.toDouble()).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {

                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    val intent = Intent(this@ReplenishBalanceActivity,ProfileActivity::class.java)
                    intent.putExtra("token", token)
                    startActivity(intent)
                }

            })
        }
    }
}