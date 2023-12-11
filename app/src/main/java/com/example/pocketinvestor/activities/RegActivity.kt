package com.example.pocketinvestor.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pocketinvestor.R
import com.example.pocketinvestor.pojo.user.RegistrationUser
import com.example.pocketinvestor.retrofit.RetrofitService
import com.example.pocketinvestor.retrofit.UserApi
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        reg()
    }
    private fun reg(){
        val inputEditUsername: TextInputEditText =findViewById(R.id.username_form)
        val inputEditFirstName: TextInputEditText = findViewById(R.id.firstname_form)
        val inputEditLastname: TextInputEditText = findViewById(R.id.lastname_form)
        val inputEditEmail: TextInputEditText = findViewById(R.id.email_form)
        val inputEditPassword: TextInputEditText = findViewById(R.id.password_form)
        val inputEditConfirmPassword: TextInputEditText = findViewById(R.id.confirm_password_form)
        val buttonSignUp: MaterialButton = findViewById(R.id.sign_up_button)
        val buttonBack: MaterialButton = findViewById(R.id.back_button)
        buttonBack.setOnClickListener {
            val intent = Intent(this@RegActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.getRetrofit().create(UserApi::class.java)
        buttonSignUp.setOnClickListener {
            val username = inputEditUsername.text.toString();
            val firstname = inputEditFirstName.text.toString();
            val lastname = inputEditLastname.text.toString();
            val email = inputEditEmail.text.toString();
            val password = inputEditPassword.text.toString();
            val confirmPassword = inputEditConfirmPassword.text.toString();
            val registrationUser = RegistrationUser(
                username,
                firstname,
                lastname,
                email,
                password,
                confirmPassword
            )
            userApi.reg(registrationUser)
                .enqueue(object : Callback<RegistrationUser> {

                    override fun onFailure(call: Call<RegistrationUser>, t: Throwable) {
                        Toast.makeText(this@RegActivity,"fail", Toast.LENGTH_LONG).show()
                        Logger.getLogger("error",t.toString())
                    }

                    override fun onResponse(
                        call: Call<RegistrationUser>,
                        response: Response<RegistrationUser>
                    ) {
                        if(response.isSuccessful) {
                            val intent = Intent(this@RegActivity,AuthActivity::class.java)
                            startActivity(intent)

                            Toast.makeText(this@RegActivity,"successful", Toast.LENGTH_LONG).show()
                        }
                        else {
                            Toast.makeText(this@RegActivity, "incorrect data", Toast.LENGTH_LONG).show()
                            Logger.getLogger("incorrect data")
                        }


                    }
                })
        }
    }
}