package com.example.healthspotapp.User

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.healthspotapp.Adapter.UsersView
import com.example.healthspotapp.Interactions.Saturations
import com.example.healthspotapp.MainActivity
import com.example.healthspotapp.R
import com.example.healthspotapp.User.Functions.History
import com.example.healthspotapp.User.Functions.SearchEngine
import com.example.healthspotapp.anyToast
import com.example.healthspotapp.databinding.ActivityUserMainBinding
import com.example.healthspotapp.spannaed
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UserMainActivity : AppCompatActivity(),Saturations {
    private val bind by lazy {
        ActivityUserMainBinding.inflate(layoutInflater)
    }
    private lateinit var fused :FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setContentView(bind.root)
        fused=LocationServices.getFusedLocationProviderClient(this)

        val shared=getSharedPreferences("user", MODE_PRIVATE)
        var string=""
        shared.getString("name","")?.trim()?.forEachIndexed { index, c ->
            string += if(index==0){
                c.uppercaseChar()
            }else{
                c
            }
        }

        bind.textView4.text= spannaed("Hi $string 😊 !!")


        bind.cycle2.let {
            it.isNestedScrollingEnabled=true
            it.layoutManager=GridLayoutManager(this,2)
            it.adapter=UsersView(this,name,image,this)
        }

    }

    val image= arrayOf(R.drawable.search,
        R.drawable.blood,
        R.drawable.bookings,
        R.drawable.logout)
    val name= arrayOf("Search\nHospital","Search\nblood donor","Appointments","Logout")
    val permission= arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION)

    @SuppressLint("MissingPermission")
    override fun getpoistion(position: Int) {
        val t=Intent(this,SearchEngine::class.java)
        when(position) {
            0 -> {
                if (permissionview()) {
                    fused.lastLocation.addOnSuccessListener {
                        if (it != null) {
                            t.putExtra("location", "${it.latitude},${it.longitude}")
                            t.putExtra("type", "Hospital")
                            startActivity(t)
                        }
                    }.addOnFailureListener {
                        anyToast(it.message)
                    }
                }
            }

            1 -> {
                if (permissionview()) {
                    t.putExtra("type", "donors")
                    startActivity(t)

                }
            }
            3->{
                dialog()
            }
            2->{
                startActivity(Intent(this,History::class.java))
            }
        }
    }

    private fun dialog() {
        MaterialAlertDialogBuilder(this).apply {
            setCancelable(false)
            setTitle("Do you want to logout ?")
            setPositiveButton("Yes"){p,_->
                p.dismiss()
                getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply()
                finishAffinity()
                startActivity(Intent(this@UserMainActivity, MainActivity::class.java))
            }
            setNegativeButton("No"){
                    o,_->
                o.dismiss()
            }
        }.show()
    }

    private fun permissionview()=
        if(ActivityCompat.checkSelfPermission(this,permission[0])!=PackageManager.PERMISSION_GRANTED
            &&ActivityCompat.checkSelfPermission(this,permission[1])!=PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permission,100)
            }else{
                ActivityCompat.requestPermissions(this,permission,100)
            }
            false
        }else{
            true
        }

}