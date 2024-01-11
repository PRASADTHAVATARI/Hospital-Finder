package com.example.healthspotapp.Hospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthspotapp.Adapter.ViewUsers
import com.example.healthspotapp.Interactions.InterRactions
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.LoginResponse
import com.example.healthspotapp.Responses.Models.User
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.anyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Doctors : AppCompatActivity() ,InterRactions{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_doc_history)
        onBackPressedDispatcher.addCallback(object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }

        })
        val id=getSharedPreferences("user", MODE_PRIVATE).getString("id","")
    ReTrofit.instance.getDoctors(condition = "getdocs", id = "$id").enqueue(object :Callback<LoginResponse>{
        override fun onResponse(
            call: Call<LoginResponse>,
            response: Response<LoginResponse>
        ) {
            response.body()?.let {
                findViewById<RecyclerView>(R.id.cycle9).apply {
                    layoutManager=LinearLayoutManager(this@Doctors)
                    adapter= it.data?.let { it1 -> ViewUsers(this@Doctors, it1,this@Doctors) }
                }
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        anyToast(t.message)
        }
    })
    }

    override fun getpositio(user: User) {
Intent(this,ViewHistory::class.java)
    .apply {
putExtra("data",user)
startActivity(this)
    }
    }
}