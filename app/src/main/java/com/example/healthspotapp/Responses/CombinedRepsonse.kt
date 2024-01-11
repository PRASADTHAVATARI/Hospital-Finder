package com.example.healthspotapp.Responses

import com.example.healthspotapp.Responses.Models.CombinedData
import com.example.healthspotapp.Responses.Models.HospitalData
import com.google.gson.annotations.SerializedName

data class CombinedRepsonse (
    @SerializedName("error"   ) var error   : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<CombinedData> = arrayListOf()
)

