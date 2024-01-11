package com.example.healthspotapp.User.Functions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthspotapp.Responses.CustomHospitalResponse
import com.example.healthspotapp.Responses.DonorResponse
import com.example.healthspotapp.Responses.Models.Donors
import com.example.healthspotapp.Responses.Models.HospitalData
import com.example.healthspotapp.Responses.ReTrofit
import com.example.healthspotapp.logview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewFactory:ViewModel() {
private val mutable= MutableLiveData(ArrayList<HospitalData>())
    private val user=MutableLiveData(ArrayList<Donors>())

    fun get_serach_hospitals(search:String) {

            ReTrofit.instance.gethospital(condition = "gethospitals", search = search)
                .enqueue(object : Callback<CustomHospitalResponse> {
                    override fun onResponse(
                        call: Call<CustomHospitalResponse>,
                        response: Response<CustomHospitalResponse>
                    ) {
                        response.body()?.let {
                            mutable.value = it.data
                        }
                    }

                    override fun onFailure(call: Call<CustomHospitalResponse>, t: Throwable) {
                        logview(t.message)
                    }
                })

    }


    fun getdonors(search: String){
ReTrofit.instance.getDonors(condition = "getmydonors", search = search).enqueue(object :Callback<DonorResponse>{
    override fun onResponse(call: Call<DonorResponse>, response: Response<DonorResponse>) {
response.body()?.let {
    user.value=it.data
}
    }

    override fun onFailure(call: Call<DonorResponse>, t: Throwable) {
    logview(t.message)
    }
})
    }

    fun getblooddonors()=user
    fun gethosptial()=mutable

}