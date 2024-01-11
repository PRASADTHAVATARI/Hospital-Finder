package com.example.healthspotapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthspotapp.Admin.AdminMainActivity
import com.example.healthspotapp.Doctor.DoctorMainActivity
import com.example.healthspotapp.Hospital.HospitalMainActivity
import com.example.healthspotapp.Responses.LoginResponse
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.User.UserMainActivity
import com.example.healthspotapp.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val cf by lazy {
        Comfun(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)


bind.signin
    .setOnClickListener {
        val mail=bind.mail.text.toString().trim()
        val passwo=bind.passwo.text.toString().trim()

        if(mail.isEmpty()){
            anyToast("Please enter your Mail-Id")
        }else if(passwo.isEmpty()){
            anyToast("Please enter your Password")
        }else if(mail.lowercase()=="admin"&& passwo.lowercase()=="admin"){
            getSharedPreferences("user", MODE_PRIVATE).edit().putString("type","admin").apply()
            finish()
            startActivity(Intent(this,AdminMainActivity::class.java))
        }else{
            cf.p.show()
            ReTrofit.instance.login(condition = "login", mail = mail, password =passwo).enqueue(
                object :Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        cf.apply {
                            p.dismiss()
                            response.body()?.let {
                                if(it.message=="success"){
                                    val k=it.data
    if(k?.isNotEmpty() == true) {
        val asit=k[0]
        getSharedPreferences("user", MODE_PRIVATE).edit().apply {
            putString("id",asit.id)
            putString("name",asit.name)
            putString("mail",asit.mail)
            putString("password",asit.password)
            putString("type",asit.type)
            putString("mobile",asit.mobile)
            apply()
        }
        finish()
        when(asit.type){
            "user"->{
                startActivity(Intent(this@LoginActivity, UserMainActivity::class.java))
            }
            "Hospital"->{
                startActivity(Intent(this@LoginActivity,HospitalMainActivity::class.java))
            }
            "Doctor"->{
                startActivity(Intent(this@LoginActivity,DoctorMainActivity::class.java))
            }
        }

    } else{
        anytoast("Invalid User")
    }
                                }else{
                                    anytoast("Invalid User")

                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                   cf.apply {
                       p.dismiss()
                       anytoast(t.message)
                   }
                    }

                }
            )
        }
    }


        bind.signup.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
                }



        }


    }
