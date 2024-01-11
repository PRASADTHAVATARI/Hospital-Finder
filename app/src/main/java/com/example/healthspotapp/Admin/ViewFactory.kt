package com.example.healthspotapp.Admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthspotapp.Responses.LoginResponse
import com.example.healthspotapp.Responses.Models.User
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.logview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewFactory:ViewModel() {
    private val mutable=MutableLiveData(ArrayList<User>())

    fun getdata(){
        ReTrofit.instance.getdata(condition = "getusers").enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let {
                    mutable.value=it.data
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
          logview(t.message)
            }


        })
    }
    fun viewdata()=mutable


}