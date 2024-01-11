package com.example.healthspotapp.Responses.Models

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class Donors (
    @SerializedName("name")var name:String?=null,
    @SerializedName("mobile")var mobile:String?=null,
    @SerializedName("furtherdetails")var furtherdetails:String?=null,
)