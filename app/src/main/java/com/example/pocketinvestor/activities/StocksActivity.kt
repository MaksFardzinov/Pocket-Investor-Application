package com.example.pocketinvestor.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketinvestor.R
import com.example.pocketinvestor.adapters.StockAdapter
import com.example.pocketinvestor.adapters.UserStocksAdapter
import com.example.pocketinvestor.pojo.stoks.StockList
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class StocksActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)
        userApi.updatePrice()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks)
        val rvStocksList  = this.findViewById<RecyclerView>(R.id.recyclerview)
        rvStocksList.layoutManager = LinearLayoutManager(this)
        val token  = intent.getStringExtra("token")

        val stocksLists =  ArrayList<StockList>()
        val buttonBack: MaterialButton = findViewById(R.id.back_button_for_stocks)
        buttonBack.setOnClickListener {
            val intent = Intent(this@StocksActivity, ProfileActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        var userStocksAdapter = StockAdapter(stocksLists,"")
        if (token!=null){
            userStocksAdapter  = StockAdapter(stocksLists,token)
        }

        rvStocksList.adapter = userStocksAdapter
        if (token != null) {
            userApi.getStocks().enqueue(object : Callback<List<StockList>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<StockList>>,
                    response: Response<List<StockList>>
                ) {
                    if(response.isSuccessful) {
                        stocksLists.addAll(response.body()!!)

                        userStocksAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<StockList>>, t: Throwable) {
                    println(t.toString())
                    Toast.makeText(this@StocksActivity, "eror", Toast.LENGTH_LONG).show()
                    Logger.getLogger("incorrect data")
                }

            })
        }
    }
}