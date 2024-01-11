package com.example.healthspotapp.Responses

import com.example.healthspotapp.Responses.Models.Beds
import com.google.gson.annotations.SerializedName
import java.lang.Error

data class BedReponse (
    @SerializedName("error")var error: Boolean?=null,
    @SerializedName("message")var message:String?=null,
    @SerializedName("data")var data:ArrayList<Beds>?=null
)
