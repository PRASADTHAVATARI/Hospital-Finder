package com.example.healthspotapp.Hospital

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthspotapp.Adapter.HistoryAdapter
import com.example.healthspotapp.Interactions.Ractions
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.CombinedRepsonse
import com.example.healthspotapp.Responses.Models.CombinedData
import com.example.healthspotapp.Responses.Models.User
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.spannaed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewHistory : AppCompatActivity() ,Ractions{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_history)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data",User::class.java)
        }else{
            intent.getParcelableExtra("data")
        }?.let {
            findViewById<TextView>(R.id.textView9).text= spannaed( "<b font =\"+2\"><u>Doctor Details</u></b></font><br>" +
                    "<big><b>${it.name}</b></big><br>" +
                    "<b>Mobile number :</b>${it.mobile}<br>" +
                    "<b>Mail-id :</b>${it.mail}<br>" +
                    "<b>Qualification :</b><br>" +
                    "${it.detailsofuser}")

            val id=getSharedPreferences("user", MODE_PRIVATE).getString("id","")
         ReTrofit.instance.gethistory(condition = "getdoconlyhistory", id = "$id").enqueue(object :Callback<CombinedRepsonse>{
             override fun onResponse(
                 call: Call<CombinedRepsonse>,
                 response: Response<CombinedRepsonse>
             ) {

                 response.body()?.let {
                     findViewById<RecyclerView>(R.id.cycle10).apply {
                     isNestedScrollingEnabled=true
                         layoutManager=LinearLayoutManager(this@ViewHistory)
                         adapter=HistoryAdapter(this@ViewHistory,it.data,this@ViewHistory)
                     }
                 }
             }

             override fun onFailure(call: Call<CombinedRepsonse>, t: Throwable) {

             }

         })
        }
    }

    override fun startfun(combinedData: CombinedData) {

    }
}