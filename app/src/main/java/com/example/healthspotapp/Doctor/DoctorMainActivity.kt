package com.example.healthspotapp.Doctor

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthspotapp.Adapter.HistoryAdapter
import com.example.healthspotapp.Comfun
import com.example.healthspotapp.Doctor.Factory.MyModel
import com.example.healthspotapp.Interactions.Ractions
import com.example.healthspotapp.MainActivity
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.CommonResponse
import com.example.healthspotapp.Responses.Models.CombinedData
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.anyToast
import com.example.healthspotapp.spannaed
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Response

class DoctorMainActivity : AppCompatActivity(),Ractions {
    private val combined by lazy {
        Comfun(this)
    }
    private var shared:SharedPreferences?= null
    private val viewfactory by lazy {
        ViewModelProvider(this)[MyModel::class.java]
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_main)
        shared=getSharedPreferences("user", MODE_PRIVATE).apply {
            val text="Hi <big>${getString("name","")} </big>😊 !!<br>" +
                    "<b>Mobile number :</b>${getString("mobile","")}<br>" +
                    "<b>Mail-id :</b>${getString("mail","")}"
            findViewById<TextView>(R.id.titleview).text = spannaed(text)
        }
       val cycle= findViewById<RecyclerView>(R.id.cycle7)
        viewfactory.getdata(shared?.getString("id",""))
        viewfactory.myobserver().observe(this){
            if(it!=null){
                cycle.layoutManager=LinearLayoutManager(this)
                cycle.adapter=HistoryAdapter(this@DoctorMainActivity,it,this@DoctorMainActivity)
            }
        }
        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            startActivity(Intent(this,MyHistory::class.java))
        }

findViewById<ImageView>(R.id.logout23).setOnClickListener {
    MaterialAlertDialogBuilder(this@DoctorMainActivity).apply {
        setCancelable(false)
        setTitle("Do you want to logout ?")
        setPositiveButton("Yes"){p,_->
            p.dismiss()
            getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply()
            finishAffinity()
            startActivity(Intent(this@DoctorMainActivity, MainActivity::class.java))
        }
        setNegativeButton("No"){
                o,_->
            o.dismiss()
        }
        show()
    }
}


    }


    override fun startfun(combinedData: CombinedData) {
MaterialAlertDialogBuilder(this).apply {
    setTitle("Do you want to accept the Appoint of ${combinedData.name} ")
    setPositiveButton("Accept"){
        p,_->
        updatefun(state="Accepted",id=combinedData.appointmentid,combinedData)
    }
    setNegativeButton("Reject"){
        p,_->
        updatefun(state="Rejected",id=combinedData.appointmentid,combinedData)
        p.dismiss()
    }
    show()
}
    }

    private fun updatefun(state: String, id: String?, combinedData: CombinedData) {
    combined.p.show()
        ReTrofit.instance.updatefun(condition = "statechange", id = "$id", state = state).enqueue(object :retrofit2.Callback<CommonResponse>{
    override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
        response.body()?.let {
            if(it.message=="Success"){
                viewfactory.filter(combinedData)
            }
            anyToast(it.message)
        }
        combined.p.dismiss()
    }

    override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
        anyToast(t.message)
        combined.p.dismiss()
    }

})
    }
}