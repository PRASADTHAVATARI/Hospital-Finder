package com.example.healthspotapp.User.Functions

import android.location.Geocoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthspotapp.Adapter.BloodDonors
import com.example.healthspotapp.Adapter.HosptialAdapters
import com.example.healthspotapp.databinding.ActivitySearchengineBinding

class SearchEngine : AppCompatActivity() {
    private val bind by lazy {
        ActivitySearchengineBinding.inflate(layoutInflater)
    }
    private val model by lazy {
        ViewModelProvider(this)[ViewFactory::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)


        val type=intent.getStringExtra("type")?.apply {
            if(this=="Hospital"){
                intent.getStringExtra("location")?.split(",")?.let {
                        val p = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            var k = ""
                            Geocoder(this@SearchEngine).getFromLocation(it[0].toDouble(), it[1].toDouble(), 1) {
                                k = it[0].locality
                            }
                            k
                        } else {
                            Geocoder(this@SearchEngine).getFromLocation(it[0].toDouble(), it[1].toDouble(), 1)
                                ?.get(0)?.locality
                        }
                        p?.let { it1 -> model.get_serach_hospitals(it1) }
                }
                model.gethosptial().observe(this@SearchEngine){
                    if(it!=null){
                        bind.cycle3.apply {
                            layoutManager=LinearLayoutManager(this@SearchEngine)
                            adapter=HosptialAdapters(this@SearchEngine,it)
                        }
                    }
                }
            }else{
                model.getdonors("")
                model.getblooddonors().observe(this@SearchEngine){
                    if(it!=null){
                        bind.cycle3.apply {
                            layoutManager=LinearLayoutManager(this@SearchEngine)
                            adapter=BloodDonors(this@SearchEngine,it)
                        }
                    }
                }
            }

        }





        bind.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if(type=="Hospital")
                        model.get_serach_hospitals(it)
                    else
                        model.getdonors(search = it)

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if(type=="Hospital")
                        model.get_serach_hospitals(it)
                    else
                        model.getdonors(search = it)
                }
                return true
            }
        })
    }
}