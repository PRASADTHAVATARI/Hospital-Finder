package com.example.healthspotapp.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthspotapp.Responses.Models.Donors
import com.example.healthspotapp.databinding.CardBinding
import com.example.healthspotapp.spannaed

class BloodDonors(val context: Context, val data:ArrayList<Donors>) :RecyclerView.Adapter<BloodDonors.Viewed>(){
    class Viewed(
val item:CardBinding
    ) :RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= Viewed(
        CardBinding.inflate(LayoutInflater.from(context),parent,false)
    )

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: Viewed, position: Int) {
    val f=data[position]
    with(holder.item){
        details.text= spannaed("<big><b>${f.name}</b></big><br>" +
                "${f.mobile}<br>" +
                "<b>Further details</b><br>" +
                "${f.furtherdetails}")
        root.setOnClickListener {
            Intent(Intent.ACTION_DIAL, Uri.parse("tel:${f.mobile}")).let {
                context.startActivity(it)
            }
        }
    }

    }
}