package com.example.healthspotapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthspotapp.Interactions.InterRactions
import com.example.healthspotapp.Interactions.Saturations
import com.example.healthspotapp.Responses.Models.User
import com.example.healthspotapp.databinding.CardBinding
import com.example.healthspotapp.logview
import com.example.healthspotapp.spannaed

class ViewUsers(
    val context:Context,
    val array:ArrayList<User>,
    val interRactions: InterRactions
):RecyclerView.Adapter<ViewUsers.Viewed>() {
    class Viewed(val item:CardBinding):RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        Viewed(
            CardBinding.inflate(
                LayoutInflater.from(context),parent,false
            )
        )

    override fun getItemCount()=array.size

    override fun onBindViewHolder(holder: Viewed, position: Int) {
    with(holder.item){
         val dataclass=array[position]
        val settext=
            when (dataclass.type) {
            "Hospital" -> {
                "<big><b>Hospital details</b></big><br>" +
                        "<b>name :</b>${dataclass.name}<br>" +
                        "<b>Mobile number :</b>${dataclass.mobile}<br>" +
                        "<b>Mail-id  :</b>${dataclass.mail}<br>" +
                        "<b>Address :</b>${dataclass.detailsofuser}"
            }
            "user" -> {
                "<big><b>User details</b></big><br>" +
                        "<b>Name</b>${dataclass.name}<br>" +
                        "<b>Mobile number :</b>${dataclass.mobile}<br>" +
                        "<b>Mail-id :</b>${dataclass.mail}<br>"
            }
            "Doctor" -> {
                root.setOnClickListener {
            interRactions.getpositio(dataclass)
                }
                        "<b>Name :</b>${dataclass.name}<br>" +
                        "<b>Mobile number :</b>${dataclass.mobile}<br>" +
                        "<b>Mail-id :</b>${dataclass.mail}<br>" +
                        "<b>Qualification :</b><br>${dataclass.detailsofuser}"

            }
            else -> {
                "<b>Nothing</b>"
            }
        }
        logview(settext)
        details.text= spannaed(settext)
    }
    }
}