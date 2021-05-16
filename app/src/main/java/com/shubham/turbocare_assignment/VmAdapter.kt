package com.shubham.turbocare_assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VmAdapter(val list: ArrayList<String>, val listener: SelectVehicleMake): RecyclerView.Adapter<VmAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var vehicleMake = itemView.findViewById<TextView>(R.id.vehicle_make)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
//                listener.OnClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle_make,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.apply {
            vehicleMake.text = item
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface MyOnClickListener{
        fun OnClick(position: Int)
    }
}