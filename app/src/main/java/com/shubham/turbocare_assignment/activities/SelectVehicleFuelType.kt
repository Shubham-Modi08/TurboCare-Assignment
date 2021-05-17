package com.shubham.turbocare_assignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubham.turbocare_assignment.adapters.Vfadapter
import com.shubham.turbocare_assignment.R

class SelectVehicleFuelType : AppCompatActivity(), Vfadapter.MyOnClickListener{


    private lateinit var title: TextView
    private lateinit var back: ImageView
    private lateinit var rcv: RecyclerView
    private lateinit var type_selected: String
    private lateinit var vehicleregno:String
    private lateinit var vehicle_make: String
    private lateinit var vehicle_model: String
    private lateinit var Vfadapter: Vfadapter
    private val list: ArrayList<String> =  ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vehicle_fuel_type)


        type_selected = intent.getStringExtra("vehicle_type")!!
        vehicle_make = intent.getStringExtra("vehicle_make")!!
        vehicleregno = intent.getStringExtra("Registration_no")!!
        vehicle_model = intent.getStringExtra("vehicle_model")!!
        getData()
        initView()

        back= findViewById((R.id.back))
        title = findViewById(R.id.title)
        title.text = getString(R.string.vehicle_fuel_type)


        back.setOnClickListener {
            onBackPressed()
        }

    }

    private fun getData(){

            list.add("Petrol")
            list.add("Diesel")
            list.add("CNG")
            list.add("Petrol + CNG")
            list.add("Electric")
            list.add("Hybrid")
        Log.d("dataarray", list.toString())
    }

    private fun initView() {
        rcv = findViewById(R.id.recycler_view_fuel_type)
        Vfadapter = Vfadapter(list,this)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = Vfadapter

    }

    override fun OnClick(position: Int) {

        intent = Intent(applicationContext, SelectVehicleTransmission::class.java)
        intent.putExtra("vehicle_type", type_selected)
        intent.putExtra("vehicle_make", list[position])
        intent.putExtra("Registration_no", vehicleregno)
        intent.putExtra("vehicle_model", list[position])
        intent.putExtra("vehicle_fuel_type", list[position])
        Log.d("fuel",list[position])
        startActivity(intent)

    }

}

