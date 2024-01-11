package com.example.healthspotapp.Doctor.Factory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthspotapp.Responses.CombinedRepsonse
import com.example.healthspotapp.Responses.Models.CombinedData
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.logview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyModel : ViewModel(){
private val mutable=MutableLiveData(ArrayList<CombinedData>())
fun getdata(id:String?){
    ReTrofit.instance.gethistory(condition = "getuserpendinghistory", id = "$id").enqueue(object :
        Callback<CombinedRepsonse> {
        override fun onResponse(
            call: Call<CombinedRepsonse>,
            response: Response<CombinedRepsonse>
        ) {
            response.body()?.let {
mutable.value=it.data
            }

        }

        override fun onFailure(call: Call<CombinedRepsonse>, t: Throwable) {
logview(t.message)
        }

    })
}

    fun filter(combinedData: CombinedData){
        val data=ArrayList<CombinedData>()
        mutable.value?.forEach {
            if(combinedData.assignedid!=it.assignedid){
                data.add(it)
            }
        }
        mutable.value=data
    }
    fun myobserver()=mutable

}
