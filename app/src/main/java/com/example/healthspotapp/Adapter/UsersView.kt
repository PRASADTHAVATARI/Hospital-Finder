package com.example.healthspotapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.healthspotapp.Interactions.Saturations
import com.example.healthspotapp.databinding.ViewcardBinding
import com.example.healthspotapp.spannaed
import kotlinx.coroutines.selects.select

class UsersView(
    val context: Context,
    val names:Array<String>,
    val images:Array<Int>,
    val viewpostion:Saturations):RecyclerView.Adapter<UsersView.MyView> (){
    class MyView(val item:ViewcardBinding):RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=MyView(
        ViewcardBinding.inflate(
            LayoutInflater.from(context),parent,false
        )
    )

    override fun getItemCount()=names.size

    override fun onBindViewHolder(holder: MyView, position: Int) {
    with(holder.item){
        image.load(images[position])
        details2.text= spannaed(names[position])
        root.setOnClickListener {
            viewpostion.getpoistion(position)
        }
    }
    }
}