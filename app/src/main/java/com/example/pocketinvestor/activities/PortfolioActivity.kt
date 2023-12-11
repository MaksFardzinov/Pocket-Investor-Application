package com.example.pocketinvestor.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketinvestor.R
import com.example.pocketinvestor.adapters.StockAdapter
import com.example.pocketinvestor.adapters.UserStocksAdapter
import com.example.pocketinvestor.pojo.user.UserStockList
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class PortfolioActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_stocks)
        val rvStocksList  = this.findViewById<RecyclerView>(R.id.recyclerview)
        rvStocksList.layoutManager = LinearLayoutManager(this)
        val stocksLists =  ArrayList<UserStockList>()
        val token  = intent.getStringExtra("token")
        var userStocksAdapter = UserStocksAdapter(stocksLists,"")
        if (token!= null){
        userStocksAdapter  = UserStocksAdapter(stocksLists,token)
        }
        rvStocksList.adapter = userStocksAdapter
        val buttonBack: MaterialButton = findViewById(R.id.back_button_for_stocks)
        buttonBack.setOnClickListener {
            val intent = Intent(this@PortfolioActivity, ProfileActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        if (token != null) {
            userApi.getUserStocks("Bearer $token").enqueue(object : Callback<List<UserStockList>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<UserStockList>>,
                    response: Response<List<UserStockList>>
                ) {
                    if(response.isSuccessful) {
                        stocksLists.addAll(response.body()!!)
                        userStocksAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<UserStockList>>, t: Throwable) {
                    Toast.makeText(this@PortfolioActivity, "eror", Toast.LENGTH_LONG).show()
                    Logger.getLogger("incorrect data")
                }

            })
    }
    }
}