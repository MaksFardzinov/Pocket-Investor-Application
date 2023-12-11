package com.example.pocketinvestor.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.pocketinvestor.R
import com.example.pocketinvestor.pojo.stoks.StockForBuyRequest
import com.example.pocketinvestor.pojo.user.UserStockList
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class UserStocksAdapter(private val usersStocksList: List<UserStockList>, private val token:String): Adapter<UserStocksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.user_stocks_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actions = usersStocksList[position]
        holder.name.text = actions.username
        holder.price.text = actions.price.toString()
        holder.count.text = actions.count.toString()
        holder.sell.setOnClickListener{
            val stockForBuyRequest = StockForBuyRequest(actions.username
            )
            val retrofitService = RetrofitService()
            val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)
            userApi.sellAction("Bearer $token",stockForBuyRequest).enqueue(object :
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
        return usersStocksList.size
    }
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var name:TextView =itemView.findViewById(R.id.stock_name)
        var price:TextView = itemView.findViewById(R.id.stock_price)
        var sell:Button = itemView.findViewById(R.id.sell_button)
        var count:TextView = itemView.findViewById(R.id.stock_count)
        }
}