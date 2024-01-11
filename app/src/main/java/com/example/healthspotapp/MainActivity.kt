package com.example.healthspotapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthspotapp.Admin.AdminMainActivity
import com.example.healthspotapp.Doctor.DoctorMainActivity
import com.example.healthspotapp.Hospital.HospitalMainActivity
import com.example.healthspotapp.User.UserMainActivity
import com.example.healthspotapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
val type=getSharedPreferences("user", MODE_PRIVATE).getString("type","")
bind.imageview.apply {
    alpha=0f
    animate().setDuration(1000).alpha(1f).withStartAction {
        overridePendingTransition(androidx.appcompat.R.anim.abc_fade_out,androidx.appcompat.R.anim.abc_fade_in)
    }.withEndAction {
        finish()
        when(type){
            "admin"->{
                startActivity(Intent(this@MainActivity,AdminMainActivity::class.java))
            }
            "user"->{
                startActivity(Intent(this@MainActivity,UserMainActivity::class.java))
            }
            "Hospital"->{
                startActivity(Intent(this@MainActivity,HospitalMainActivity::class.java))
            }
            "Doctor"->{
                startActivity(Intent(this@MainActivity,DoctorMainActivity::class.java))
            }
        else->{
    startActivity(Intent(this@MainActivity,LoginActivity::class.java))
        }
        }
    }
}

    }
}