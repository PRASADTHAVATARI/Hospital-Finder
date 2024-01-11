package com.example.healthspotapp.Responses.Models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class HospitalData (
    @SerializedName("id"             ) var id             : String? = null,
    @SerializedName("hospitalid"     ) var hospitalid     : String? = null,
    @SerializedName("bedavailabilty" ) var bedavailabilty : String? = null,
    @SerializedName("name"           ) var name           : String? = null,
    @SerializedName("mail"           ) var mail           : String? = null,
    @SerializedName("password"       ) var password       : String? = null,
    @SerializedName("type"           ) var type           : String? = null,
    @SerializedName("mobile"         ) var mobile         : String? = null,
    @SerializedName("detailsofuser"  ) var detailsofuser  : String? = null
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(hospitalid)
        parcel.writeString(bedavailabilty)
        parcel.writeString(name)
        parcel.writeString(mail)
        parcel.writeString(password)
        parcel.writeString(type)
        parcel.writeString(mobile)
        parcel.writeString(detailsofuser)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HospitalData> {
        override fun createFromParcel(parcel: Parcel): HospitalData {
            return HospitalData(parcel)
        }

        override fun newArray(size: Int): Array<HospitalData?> {
            return arrayOfNulls(size)
        }
    }

}
