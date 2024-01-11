package com.example.healthspotapp.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import coil.load
import com.example.healthspotapp.Comfun
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.CommonResponse
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.anyToast
import com.example.healthspotapp.databinding.ActivityAddUserBinding
import com.example.healthspotapp.spannaed
import okhttp3.internal.http2.Http2
import retrofit2.Call
import retrofit2.Response

class AddUser : AppCompatActivity() {
    private val bind by lazy {
        ActivityAddUserBinding.inflate(layoutInflater)
    }
    private val comfun by lazy {
        Comfun(this)
    }
    private var match = Regex("@gmail.com")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        val type = intent.getStringExtra("type")?.apply {
            with(bind) {
                textView2.text = this@apply
                when (this@apply) {
                    "Donor" -> {
                        mystateer.text = spannaed("Donor further details")
                        state.text = spannaed("Donor Name")
                        mail.text = spannaed("Donor Mail-id")
                        addres.apply {
                            hint = spannaed("Donor details")
                            gravity = Gravity.BOTTOM
                            height = 250
                        }
                        mail.isVisible=false
                        mailid1.isVisible=false
                        pass.isVisible=false
                        password1.isVisible=false
                        myicon.load(R.drawable.building)
                    }

                    "Hospital" -> {
                        myicon.load(R.drawable.blood)
                       functions(this@apply)
                        addres.apply {
                            hint = spannaed("Further details")
                            gravity = Gravity.BOTTOM
                            height = 200
                        }
                    }
                    "Doctor"->{
                        myicon.load(R.drawable.adddoctor)
                         functions(this@apply)

                        addres.apply {
                            hint = spannaed("Graduation details")
                            gravity = Gravity.BOTTOM
                            height = 200
                        }
                    }
                }
            }
        }

        bind.createac.setOnClickListener {
            val name1 = bind.name1.text.toString().trim()
            val mailid1 = bind.mailid1.text.toString().trim()
            val password1 = bind.password1.text.toString().trim()
            val mobile1 = bind.mobile1.text.toString().trim()
            val addres = bind.addres.text.toString().trim()
            if (name1.isEmpty()) {
                bind.name1.error = "Please enter $type name"
            } else
                if (mailid1.isEmpty()&&type=="Hospital") {
                    bind.mailid1.error = "Please enter $type mail-i"
                } else if (password1.isEmpty()&&type=="Hospital") {
                        bind.password1.error = "Please enter $type passwords"
                    } else if (mobile1.isEmpty()) {
                            bind.mobile1.error = "Please enter $type mobiles"
                            } else if (addres.isEmpty()) {
                                bind.addres.error = "Please enter $type address"
                            } else if (!mailid1.contains("@gmail.com")&&type==="Hospital") {
                                bind.mailid1.error = "Please enter a valid valid mail"
                            } else if (mobile1.length != 10) {
                                bind.mobile1.error = "Please enter a valid mobile number of $type"
                            } else {
                                val id=getSharedPreferences("user", MODE_PRIVATE).getString("id",null)
                                    ?: "Nothing"

                                comfun.p.show()
                                ReTrofit.instance.create(
                                    name = name1,
                                    mail = mailid1,
                                    password = password1,
                                    type = "$type",
                                    mobile = mobile1,
                                    details = addres,
                                    id=id
                                ).enqueue(object : retrofit2.Callback<CommonResponse> {
                                    override fun onResponse(
                                        call: Call<CommonResponse>,
                                        response: Response<CommonResponse>
                                    ) {
                                        response.body()?.let {
                                            if (it.message == "Success") {
                                                anyToast("Created")
                                                finish()
                                            } else {
                                                anyToast(it.message)
                                            }
                              }
                            comfun.p.dismiss()


                                    }


                                    override fun onFailure(
                                        call: Call<CommonResponse>,
                                        t: Throwable
                                    ) {
                                        comfun.p.dismiss()
                                        anyToast(t.message)
                                    }
                                })
                            }
        }




























        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

    }

    private fun functions(state2: String) {
        bind.apply {
            state.text = spannaed("$state2 Name")
            mail.text = spannaed("$state2 Mail-id")

            mystateer.text =if(state2=="Doctor") spannaed("$state2 graduations") else spannaed("$state2 Address")
        }
    }

}
