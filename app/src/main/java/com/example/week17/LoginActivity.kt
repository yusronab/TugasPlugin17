package com.example.week17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week17.databinding.ActivityLoginBinding
import com.example.week17.model.User
import com.example.week17.response.SingleResponse
import com.example.week17.webservice.APIservice
import com.example.week17.webservice.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnLogin()
        btnRegister()
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }

    private fun btnRegister(){
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun isLogin(){
        val token = Constant.getToken(this)
        if (!token.equals("UNDEF")){
            startActivity(Intent(this, MainActivity::class.java)).also { finish() }
        }
    }

    fun doLogin(){
        val username = binding.etLoginUsername.text.toString()
        val password = binding.etLoginPassword.text.toString()
        APIservice.APIendPoint().login(username, password).enqueue(object : Callback<SingleResponse<User>>{
            override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        Constant.setToken(this@LoginActivity, body.data.token)
                        Toast.makeText(applicationContext, "hi ${body.data.username}", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }

            override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun btnLogin(){
       binding.btnLogin.setOnClickListener {
           doLogin()
       }
    }
}