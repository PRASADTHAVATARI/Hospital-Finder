package com.example.healthspotapp.Responses

import com.google.android.gms.common.internal.service.Common
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @FormUrlEncoded
    @POST("signup.php")
    fun create(
    @Field("name")name:String,
    @Field("mail")mail:String,
    @Field("password")password:String,
    @Field("type")type:String,
    @Field("mobile")mobile:String,
    @Field("details")details:String,
    @Field("id")id:String,
    ):Call<CommonResponse>

    @FormUrlEncoded
    @POST("function.php")
    fun login(
        @Query("condition")condition:String,
        @Field("mail")mail:String,
        @Field("password")password:String
    ):Call<LoginResponse>

    @GET("function.php")
    fun getdata(
        @Query("condition")condition:String
    ):Call<LoginResponse>

    @FormUrlEncoded
        @POST("function.php")
     fun getbeds(
        @Query("condition")condition:String,
        @Field("id") id: String):Call<BedReponse>

    @FormUrlEncoded
    @POST("updatefun.php")
    fun updateFileds(
        @Query("condition")condition:String,
        @Field("id")id:String,
        @Field("seats")seats:String
    ):Call<CommonResponse>

    @FormUrlEncoded
    @POST("function.php")
    fun gethospital(
        @Query("condition")condition:String,
        @Field("search")search:String
    ):Call<CustomHospitalResponse>

    @FormUrlEncoded
    @POST("function.php")
    fun getDonors(
        @Query("condition")condition:String,
        @Field("search")search:String
    ):Call<DonorResponse>

@FormUrlEncoded
@POST("function.php")
fun getDoctors(
    @Query("condition")condition:String,
    @Field("id") id:String)
:Call<LoginResponse>

@FormUrlEncoded
@POST("addappointments.php")
fun addappointment(
    @Field("doctorid")doctorid:String,
    @Field("hospitalid")hospitalid:String,
    @Field("userid")userid:String,
    @Field("dateof")dateof:String,
    @Field("timings")timings:String,
    @Field("reasons")reasons:String,
):Call<CommonResponse>

@FormUrlEncoded
@POST("function.php")
    fun gethistory(
        @Query("condition")condition:String,
        @Field("id")id:String
    ):Call<CombinedRepsonse>



    @FormUrlEncoded
    @POST("updatefun.php")
    fun updatefun(
        @Query("condition")condition:String,
        @Field("id")id:String,
        @Field("state")state:String
    ):Call<CommonResponse>


}
