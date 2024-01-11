package com.example.healthspotapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.OverScroller
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthspotapp.Adapter.ViewUsers
import com.example.healthspotapp.Interactions.InterRactions
import com.example.healthspotapp.MainActivity
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.Models.User
import com.example.healthspotapp.databinding.ActivityAdminMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AdminMainActivity : AppCompatActivity() ,InterRactions{
    private val bind by lazy {
        ActivityAdminMainBinding.inflate(layoutInflater)
    }
    private val model by lazy {
        ViewModelProvider(this)[ViewFactory::class.java]
    }
    private val dialog by lazy {
        MaterialAlertDialogBuilder(this).apply {
            setCancelable(false)
            setTitle("Do you want to logout ?")
            setPositiveButton("Yes"){p,_->
                p.dismiss()
                getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply()
                finishAffinity()
                startActivity(Intent(this@AdminMainActivity,MainActivity::class.java))
            }
            setNegativeButton("No"){
                o,_->
                o.dismiss()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        val intent=Intent(this,AddUser::class.java)
        bind.personadd.setOnClickListener {
            intent.putExtra("type","Donor")
            startActivity(intent)
        }
        bind.addhospital.setOnClickListener {
            intent.putExtra("type","Hospital")
            startActivity(intent)
        }



        bind.logout.setOnClickListener{
            dialog.show()
        }

        model.getdata()
        model.viewdata().observe(this){

            if(it!=null){
                bind.cycle.apply {
                    layoutManager=LinearLayoutManager(this@AdminMainActivity)
                    overScrollMode=0
                    adapter=ViewUsers(this@AdminMainActivity,it,this@AdminMainActivity)

                }
            }
        }

    }


    override fun onResume() {
        super.onResume()
        model.getdata()
    }

    override fun getpositio(string: User) {

    }

}