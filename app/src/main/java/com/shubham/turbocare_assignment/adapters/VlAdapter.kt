package com.shubham.turbocare_assignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubham.turbocare_assignment.R
import com.shubham.turbocare_assignment.database.VehicleEntity

class VlAdapter(private val list: ArrayList<VehicleEntity>, val listener: MyOnClickListener): RecyclerView.Adapter<VlAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var vehicleRegno = itemView.findViewById<TextView>(R.id.registration_no)
        var vehicleMake = itemView.findViewById<TextView>(R.id.vehicle_make)
        var vehicleModel = itemView.findViewById<TextView>(R.id.vehicle_model)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                listener.OnClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_vehicle,parent,false)
        return MyViewHolder(view)
    }




    override fun onBindViewHolder(holder: VlAdapter.MyViewHolder, position: Int) {
        val item = list[position]
        holder.apply {
            vehicleRegno.text = item.Reg_no
            vehicleMake.text = item.Vehicle_make
            vehicleModel.text = item.Vehicle_model
        }
    }



    override fun getItemCount(): Int {
        return list.size
    }

    interface MyOnClickListener{
        fun OnClick(position: Int)
    }




}