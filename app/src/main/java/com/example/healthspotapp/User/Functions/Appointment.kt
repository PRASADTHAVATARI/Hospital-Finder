package com.example.healthspotapp.User.Functions

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthspotapp.Adapter.UsersView
import com.example.healthspotapp.Adapter.ViewUsers
import com.example.healthspotapp.Comfun
import com.example.healthspotapp.Hospital.HospitalMainActivity
import com.example.healthspotapp.Interactions.InterRactions
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.CommonResponse
import com.example.healthspotapp.Responses.LoginResponse
import com.example.healthspotapp.Responses.Models.HospitalData
import com.example.healthspotapp.Responses.Models.User
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.User.UserMainActivity
import com.example.healthspotapp.anyToast
import com.example.healthspotapp.databinding.ActivityAppointmentBinding
import com.example.healthspotapp.databinding.AppointmentsBinding
import com.example.healthspotapp.logview
import com.example.healthspotapp.spannaed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Appointment : AppCompatActivity() ,InterRactions{
    private val bind by lazy {
        ActivityAppointmentBinding.inflate(layoutInflater)
    }
    val cf by lazy {
        Comfun(this)
    }
    val mybind by lazy {
        AppointmentsBinding.inflate(layoutInflater)
    }
    private val dialog by lazy {
        Dialog(this).apply {
            setContentView(mybind.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data",HospitalData::class.java)
        }else{
            intent.getParcelableExtra("data")
        }?.let {

         bind.details4.text= spannaed(
                 "<b font =\"+2\"><u>Hospital Details</u></font><br>" +
                 "<big><b>${it.name}</b></big><br>" +
                 "<b>Mobile number :</b>${it.mobile}<br>" +
                 "<b>Mail-id :</b>${it.mail}<br>" +
                 "<b>Further details :</b><br>" +
                 "${it.detailsofuser}")

         cf.p.show()

         ReTrofit.instance.getDoctors(condition = "getdocs", id =it.hospitalid!! ).enqueue(object :Callback<LoginResponse>{
             override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                 response.body()?.let {
                     bind.cycle5.apply {
                         isNestedScrollingEnabled=true
                         layoutManager=LinearLayoutManager(this@Appointment)
                         it.data?.let { it1 ->
                           adapter=ViewUsers(this@Appointment, it1,this@Appointment)
                         }

                     }
                 }
                 cf.p.dismiss()
             }

             override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                 cf.p.dismiss()
                 anyToast(t.message)
             }
         })

     }




    }

    override fun getpositio(string: User) {
        val id=getSharedPreferences("user", MODE_PRIVATE).getString("id","")
        dialog.show()
        mybind.apply {
            apdate.setText("")
            timings.setText("")
            reasons.setText("")

            fixdate.setOnClickListener{
                val apdate=apdate.text.toString().trim()
                val timings=timings.text.toString().trim()
                val reasons=reasons.text.toString().trim()
                if(apdate.isEmpty()){
                    anyToast("Please enter appointment timings")
                }else if(timings.isEmpty()){
                    anyToast("Please enter appointment timings")
                }else if(reasons.isEmpty()){
                    anyToast("Please enter appointment timings")
                }else{
                    cf.p.show()
                    ReTrofit.instance.addappointment(
                        doctorid = "${string.id}",
                        hospitalid = "${string.assignedid}",
                        userid = "$id",
                        dateof = apdate,
                        timings = timings,
                        reasons = reasons
                    ).enqueue(object :Callback<CommonResponse>{
                        override fun onResponse(
                            call: Call<CommonResponse>,
                            response: Response<CommonResponse>
                        ) {
                            response.body()?.let {
                                if(it.message=="Success"){
                                    finishAffinity()
                                    startActivity(Intent(this@Appointment,UserMainActivity::class.java))
                                    anyToast("Success")
                                }else{
                                    anyToast(it.message)
                                }
                            }
                            dialog.dismiss()
                            cf.p.dismiss()
                        }

                        override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                         anyToast(t.message)
                            dialog.dismiss()
                            cf.p.dismiss()
                        }
                    })
                }
            }
           dialog.show()

        }
    }
}