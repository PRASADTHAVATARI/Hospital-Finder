package com.example.healthspotapp.Responses

import com.example.healthspotapp.Responses.Models.Donors
import com.google.gson.annotations.SerializedName

data class DonorResponse(
    @SerializedName("error")var error:String?=null,
    @SerializedName("message")var message:String?=null,
    @SerializedName("data")var data:ArrayList<Donors>?= arrayListOf()
) {
}