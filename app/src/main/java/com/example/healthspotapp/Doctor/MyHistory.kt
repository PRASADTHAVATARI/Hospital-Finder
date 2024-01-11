package com.example.healthspotapp.Doctor

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthspotapp.Adapter.HistoryAdapter
import com.example.healthspotapp.Interactions.Ractions
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.CombinedRepsonse
import com.example.healthspotapp.Responses.CommonResponse
import com.example.healthspotapp.Responses.Models.CombinedData
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.anyToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyHistory : AppCompatActivity() ,Ractions{
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_history)
    onBackPressedDispatcher.addCallback(object :OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            finish()
        }
    })

        val id=getSharedPreferences("user", MODE_PRIVATE).getString("id","")

        ReTrofit.instance.gethistory(condition = "getdoctorhistory", id = "$id").enqueue(object :Callback<CombinedRepsonse>{
            override fun onResponse(
                call: Call<CombinedRepsonse>,
                response: Response<CombinedRepsonse>
            ) {
                response.body()?.let {
                    findViewById<RecyclerView>(R.id.cycle8).apply {
                        layoutManager=LinearLayoutManager(this@MyHistory)
                        adapter=HistoryAdapter(this@MyHistory,it.data,this@MyHistory)
                    }
                }

            }

            override fun onFailure(call: Call<CombinedRepsonse>, t: Throwable) {
                anyToast(t.message)
            }
        })

    }

    override fun startfun(combinedData: CombinedData) {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Update the status of ${combinedData.name} As Complete ?")
            setPositiveButton("completed"){m,_->
                m.dismiss()

                changemymine(combinedData.appointmentid)
            }
            setNegativeButton("cancel"){p,_->
                p.dismiss()
            }
            show()

        }
    }

    private fun changemymine(id: String?) {

        ReTrofit.instance.updatefun(condition = "statechange", id = "$id", state = "Completed").enqueue(object :retrofit2.Callback<CommonResponse>{
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                response.body()?.let {
                    if(it.message=="Success"){
                        anyToast(any="Updated")
                    }
                    anyToast(it.message)
                }
            }

            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                anyToast(t.message)
            }

        })
    }
}