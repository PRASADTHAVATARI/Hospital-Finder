package com.example.healthspotapp.Responses

import com.example.healthspotapp.Responses.Models.HospitalData
import com.google.gson.annotations.SerializedName

data class CustomHospitalResponse (
    @SerializedName("error"   ) var error   : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<HospitalData> = arrayListOf()
)