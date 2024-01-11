package com.example.healthspotapp.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.healthspotapp.R
import com.example.healthspotapp.Responses.CustomHospitalResponse
import com.example.healthspotapp.Responses.Models.HospitalData
import com.example.healthspotapp.User.Functions.Appointment
import com.example.healthspotapp.databinding.CardBinding
import com.example.healthspotapp.databinding.CustomviewBinding
import com.example.healthspotapp.spannaed

class HosptialAdapters(
    val context: Context,
    val array:ArrayList<HospitalData>
) :RecyclerView.Adapter<HosptialAdapters.ViewHold>(){
    class ViewHold (
        val item:CustomviewBinding
    ):RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHold(
            CustomviewBinding.inflate(LayoutInflater.from(context),parent,false)
        )

    override fun getItemCount()=array.size

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
    with(holder.item){
        image.load(R.drawable.bed)
        val j=array[position]
        details3.text= spannaed(
                "<big></b>${j.name}</b></big><br>" +
                "<b>Mail id :</b>${j.mail}<br>" +
                "<b>Mobile :</b>${j.mobile}<br>" +
                "<b>Further details : </b><br>${j.detailsofuser}")
        seats.text=j.bedavailabilty
        root.setOnClickListener {

            Intent(context,Appointment::class.java).apply {
                putExtra("data",j)
                context.startActivity(this)
            }
        }
        call.setOnClickListener {
            Intent(Intent.ACTION_DIAL,Uri.parse("tel:${j.mobile}")).let {
                context.startActivity(it)
            }
        }
    }
    }
}