package com.example.healthspotapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.example.healthspotapp.Responses.CommonResponse
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.databinding.ActivitySignupBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Signup : AppCompatActivity() {
    private val bind by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    private val cf by lazy {
        Comfun(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        bind.login.setOnClickListener { finish() }
        bind.btnsignup.setOnClickListener {
            val name = bind.name.text.toString().trim()
            val mailid = bind.mailid.text.toString().trim()
            val password = bind.password.text.toString().trim()
            val mobile = bind.mobile.text.toString().trim()

            if (name.isEmpty()) {
                anyToast("Please enter your Name")
            } else if (mailid.isEmpty()) {
                anyToast("Please enter your Mail-id")
            } else if (password.isEmpty()) {
                anyToast("Please enter your Password")
            } else if (mobile.isEmpty()) {
                anyToast("Please enter your Mobile number")
            } else if (!mailid.contains("@gmail.com")) {
                anyToast("Please enter a valid Mail-id")
            } else if (mobile.length != 10) {
                anyToast("Please enter a valid mobile number")
            } else {
                cf.p.show()
                CoroutineScope(IO).launch {
                    ReTrofit.instance.create(
                        name = name,
                        mail = mailid,
                        password = password,
                        type = "user",
                        mobile = mobile,
                        details = "Nothing For user",
                        id="Nothing"
                    ).enqueue(object : Callback<CommonResponse> {
                        override fun onResponse(
                            call: Call<CommonResponse>,
                            response: Response<CommonResponse>
                        ) {
                            cf.apply {
                                p.dismiss()
                                val p = response.body()?.apply {
            if(message=="Success"){
                          finish()
                    }
                                    anytoast(message)
                                }
                                if(p==null){
                                    anytoast("Server Error")
                                }
                            }

                        }

                        override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                            cf.apply {
                                anytoast(t.message)
                                p.dismiss()
                            }
                        }
                    })


                }
            }

            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finish()
                }

            })

        }
    }
}