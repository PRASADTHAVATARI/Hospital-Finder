package com.example.healthspotapp.Adapter

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthspotapp.Interactions.Ractions
import com.example.healthspotapp.Responses.Models.CombinedData
import com.example.healthspotapp.Responses.Models.HospitalData
import com.example.healthspotapp.databinding.CardBinding
import com.example.healthspotapp.spannaed

class HistoryAdapter(
    var context: Context,
    var array: ArrayList<CombinedData>,
    var completed: Ractions
) : RecyclerView.Adapter<HistoryAdapter.Holder>() {
    class Holder(val item:CardBinding):RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= Holder(
        CardBinding.inflate(LayoutInflater.from(context),parent,false)
    )

    override fun getItemCount()=array.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.item){
            val j=array[position]
            details.text= spannaed("<big>${j.name}</big><br>" +
                    "<b>Status :</b>${j.state}<br>" +
                    "<b>Appointment date:</b>${j.dateof}<br>" +
                    "<b>Timings : </b>${j.timings}<br>" +
                    "<b>Reason :</b><br>" +
                    "${j.reasons}")
root.setOnClickListener {
    completed.startfun(j)
}
        }
    }

}
