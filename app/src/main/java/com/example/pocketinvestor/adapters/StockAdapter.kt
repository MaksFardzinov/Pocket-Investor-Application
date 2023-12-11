package com.example.pocketinvestor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketinvestor.R
import com.example.pocketinvestor.pojo.stoks.StockForBuyRequest
import com.example.pocketinvestor.pojo.stoks.StockList
import com.example.pocketinvestor.pojo.user.UserStockList
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class StockAdapter(private val stockList: List<StockList>, private val token:String): RecyclerView.Adapter<StockAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.stocks_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actions = stockList[position]
        holder.name.text = actions.username
        holder.description.text = actions.description
        holder.price.text = actions.price.toString()
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)
        holder.buy.setOnClickListener {
            val stockForBuyRequest = StockForBuyRequest(actions.username
            )
            userApi.buyAction("Bearer $token",stockForBuyRequest).enqueue(object :
                Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Logger.getLogger("incorrect data")
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return stockList.size
    }
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.stock_name)
        val description: TextView = itemView.findViewById(R.id.stock_description)
        var price: TextView = itemView.findViewById(R.id.stock_price)
        var buy: Button = itemView.findViewById(R.id.buy_button)
    }
}