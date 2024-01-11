package com.example.healthspotapp.Hospital

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.healthspotapp.Adapter.UsersView
import com.example.healthspotapp.Admin.AddUser
import com.example.healthspotapp.Comfun
import com.example.healthspotapp.Interactions.Saturations
import com.example.healthspotapp.MainActivity
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.BedReponse
import com.example.healthspotapp.Responses.CommonResponse
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.anyToast
import com.example.healthspotapp.databinding.ActivityHospitalMainBinding
import com.example.healthspotapp.databinding.DialogboxBinding
import com.example.healthspotapp.spannaed
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Response

class HospitalMainActivity : AppCompatActivity() ,Saturations{
    private val bind by lazy {
        ActivityHospitalMainBinding.inflate(layoutInflater)
    }
    private val cf by lazy {
        Comfun(this)
    }
    private val dialog by  lazy {
        DialogboxBinding.inflate(layoutInflater)
    }
    private val box by lazy {
        Dialog(this@HospitalMainActivity).apply {
            setContentView(dialog.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        bind.cycle4.let {
            it.layoutManager=GridLayoutManager(this,2)
            it.isHorizontalScrollBarEnabled=true
            it.adapter=UsersView(this,names,image,this)
        }
        getSharedPreferences("user", MODE_PRIVATE).apply {
            val text="Hi <b>${getString("name","")}</b>\uD83D\uDE0A !!<br>" +
                    "<b>Mobile number :</b>${getString("mobile","")}<br>" +
                    "<b>Mail-id :</b>${getString("mail","")}<br>" +
                    "<b>Further details :</b>${getString("detailsofuser","")}<br>"
            bind.details5.text= spannaed(text)
        }
    }

    val image= arrayOf(R.drawable.bed,
        R.drawable.bookings,
        R.drawable.adddoctor
        ,R.drawable.logout)
    val names= arrayOf("Beds","Appointments","Add doctor","Logout")


    override fun getpoistion(position: Int)
    {
        val id=getSharedPreferences("user", MODE_PRIVATE).getString("id","")!!
when(position){
    0->{
        cf.p.show()
        ReTrofit.instance.getbeds(condition = "getbeds", id = id).enqueue(object :retrofit2.Callback<
                BedReponse>{
            @SuppressLint("ServiceCast")
            override fun onResponse(call: Call<BedReponse>, response: Response<BedReponse>) {
                response.body()?.let {
                    val data=it.data
                    if(data!=null){
                        if(data.isNotEmpty()){
                             dialog.availa.apply {

                                 setText(data[0].bedavailabilty)
                                 requestFocus()
                                 val system=getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
                                 system.showSoftInput(this,InputMethodManager.SHOW_IMPLICIT)
                             }
                                dialog.update.setOnClickListener {
                                    updatebeds(id)
                                }

                        }else{
                            dialog.update.setOnClickListener {
                                updatebeds(id)
                            }
                        }
                        box.show()
                    }

                }

                cf.p.dismiss()

            }

            override fun onFailure(call: Call<BedReponse>, t: Throwable) {
            cf.p.dismiss()
            }

        })
    }


1->{
    startActivity(Intent(this,Doctors::class.java))
}

    2->{
        val int=Intent(this,AddUser::class.java)
        int.putExtra("type","Doctor")
        startActivity(int)
    }


    3->{
        dialogview()
    }



}


    }

    private fun dialogview() {
        MaterialAlertDialogBuilder(this).apply {
            setCancelable(false)
            setTitle("Do you want to logout ?")
            setPositiveButton("Yes"){p,_->
                p.dismiss()
                getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply()
                finishAffinity()
                startActivity(Intent(this@HospitalMainActivity, MainActivity::class.java))
            }
            setNegativeButton("No"){
                    o,_->
                o.dismiss()
            }
        }.show()
    }

    private fun updatebeds(id: String) {
        val beds=dialog.availa.text.toString().trim()
        if(beds.isEmpty()){
            dialog.availa.error="Please enter bed Availability"
        }else{
            box.dismiss()
            cf.p.show()
            ReTrofit.instance.updateFileds(condition = "udpateseats",id = id,
                seats = beds).enqueue(object :retrofit2.Callback<
                    CommonResponse>{
                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    dialog.availa.setText("")
                    response.body()?.let {
                        if(it.message=="Success"){
                        anyToast("Updated")
                        }else{
                            anyToast(it.message)
                        }
                    }
                    cf.p.dismiss()

                }

                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    anyToast(t.message)
                    dialog.availa.setText("")
                    cf.p.dismiss()
                }

            })
        }
    }
}