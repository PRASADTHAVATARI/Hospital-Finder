package com.example.healthspotapp.User.Functions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthspotapp.Adapter.HistoryAdapter
import com.example.healthspotapp.Comfun
import com.example.healthspotapp.Doctor.Factory.MyModel
import com.example.healthspotapp.Interactions.Ractions
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.CombinedRepsonse
import com.example.healthspotapp.Responses.Models.CombinedData
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.databinding.ActivityHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class History : AppCompatActivity() ,Ractions{
    private val bind by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }
    private val dialog by lazy {
        Comfun(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        dialog.p.show()
        val id=getSharedPreferences("user", MODE_PRIVATE).getString("id","")
        ReTrofit.instance.gethistory(condition = "gethistory", id = "$id").enqueue(object :Callback<CombinedRepsonse>{
            override fun onResponse(
                call: Call<CombinedRepsonse>,
                response: Response<CombinedRepsonse>
            ) {
                response.body()?.let {
                    it.data.let {
                        bind.cycle6.apply {
                            layoutManager=LinearLayoutManager(this@History)
                            adapter= HistoryAdapter(this@History,it,this@History)
                        }
                    }

                }
                dialog.p.dismiss()
            }

            override fun onFailure(call: Call<CombinedRepsonse>, t: Throwable) {
            dialog.p.dismiss()
            }

        })
    }

    override fun startfun(combinedData: CombinedData) {

    }
}