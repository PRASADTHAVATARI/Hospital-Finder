package com.example.healthspotapp.Responses.Models

import com.google.gson.annotations.SerializedName

data class CombinedData (
    @SerializedName("id"            ) var id            : String? = null,
    @SerializedName("doctorid"      ) var doctorid      : String? = null,
    @SerializedName("hospitalid"    ) var hospitalid    : String? = null,
    @SerializedName("userid"        ) var userid        : String? = null,
    @SerializedName("dateof"        ) var dateof        : String? = null,
    @SerializedName("timings"       ) var timings       : String? = null,
    @SerializedName("reasons"       ) var reasons       : String? = null,
    @SerializedName("state"         ) var state         : String? = null,
    @SerializedName("name"          ) var name          : String? = null,
    @SerializedName("mail"          ) var mail          : String? = null,
    @SerializedName("password"      ) var password      : String? = null,
    @SerializedName("type"          ) var type          : String? = null,
    @SerializedName("mobile"        ) var mobile        : String? = null,
    @SerializedName("detailsofuser" ) var detailsofuser : String? = null,
    @SerializedName("assignedid"    ) var assignedid    : String? = null,
    @SerializedName("appointmentid"    ) var appointmentid    : String? = null

)