package com.example.healthspotapp.Responses.Models

import com.google.gson.annotations.SerializedName

data class Beds (
    @SerializedName("id")var id:Int?=null,
    @SerializedName("hospitalid")var hospitalid:String?=null,
    @SerializedName("bedavailabilty")var bedavailabilty:String?=null,
)
