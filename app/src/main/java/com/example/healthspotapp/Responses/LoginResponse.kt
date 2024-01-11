package com.example.healthspotapp.Responses

import com.example.healthspotapp.Responses.Models.User
import com.google.gson.annotations.SerializedName
import java.lang.Error

data class LoginResponse (
   @SerializedName("error") var error: Boolean,
   @SerializedName("message") var message:String,
   @SerializedName("data") var data:ArrayList<User>?= arrayListOf()
)