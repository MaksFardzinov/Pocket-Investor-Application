package com.example.pocketinvestor.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pocketinvestor.R
import com.example.pocketinvestor.pojo.JwtRequest
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        auth()
    }
    private fun auth(){
        val inputEditUsername: TextInputEditText =findViewById(R.id.username_form)
        val inputEditPassword: TextInputEditText = findViewById(R.id.password_form)
        val buttonAuth: MaterialButton = findViewById(R.id.auth_button)
        val buttonBack: MaterialButton = findViewById(R.id.back_button)
        buttonBack.setOnClickListener {
            val intent = Intent(this@AuthActivity, MainActivity::class.java)
            startActivity(intent)
        }
        buttonAuth.setOnClickListener {
            val username = inputEditUsername.text.toString();
            val password = inputEditPassword.text.toString();
            val jwtRequest = JwtRequest(username, password)
            val retrofitService = RetrofitService()
            val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)
            userApi.auth(jwtRequest)
                .enqueue(object : Callback<JwtRequest> {

                    override fun onFailure(call: Call<JwtRequest>, t: Throwable) {
                        Toast.makeText(this@AuthActivity, "fail", Toast.LENGTH_LONG).show()
                        Logger.getLogger("error", t.toString())
                    }

                    override fun onResponse(
                        call: Call<JwtRequest>,
                        response: Response<JwtRequest>
                    ) {
                        Toast.makeText(this@AuthActivity, "successful", Toast.LENGTH_LONG).show()

                    }
                })
        }
    }
}
