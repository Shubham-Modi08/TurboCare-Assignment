package com.shubham.turbocare_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectVehicleFuelType : AppCompatActivity() {


    private lateinit var title: TextView
    private lateinit var back: ImageView
    private lateinit var rcv: RecyclerView
    private lateinit var type_selected: String
    private lateinit var vehicleregno:String
    private lateinit var vehicle_make: String
    private lateinit var vehicle_model: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vehicle_fuel_type)


        type_selected = intent.getStringExtra("vehicle_type")!!
        vehicle_make = intent.getStringExtra("vehicle_make")!!
        vehicleregno = intent.getStringExtra("Registration_no")!!
        vehicle_model = intent.getStringExtra("vehicle_model")!!


        back= findViewById((R.id.back))
        title = findViewById(R.id.title)
        title.text = getString(R.string.vehicle_fuel_type)

        back.setOnClickListener {
            onBackPressed()
        }

    }
}