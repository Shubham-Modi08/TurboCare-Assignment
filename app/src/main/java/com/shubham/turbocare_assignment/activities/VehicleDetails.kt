package com.shubham.turbocare_assignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.shubham.turbocare_assignment.R

class VehicleDetails : AppCompatActivity() {


    private lateinit var back: ImageView
    private lateinit var title: TextView
    private lateinit var model_name: TextView
    private lateinit var model_make: TextView
    private lateinit var model_type: TextView
    private lateinit var model_transmission: TextView
    private lateinit var vehicleregno:String
    private lateinit var vehicle_make: String
    private lateinit var vehicle_model: String
    private lateinit var vehicle_fuel_type: String
    private lateinit var vehicle_transmission: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details)

        vehicle_make = intent.getStringExtra("vehicle_make")!!
        vehicle_model = intent.getStringExtra("vehicle_model")!!
        vehicleregno = intent.getStringExtra("Registration_no")!!
        vehicle_fuel_type = intent.getStringExtra("vehicle_fuel_type")!!
        vehicle_transmission = intent.getStringExtra("vehicle_transmission")!!

        Log.d("make",vehicle_make)
        Log.d("model",vehicle_model)
        Log.d("type",vehicle_fuel_type)
        Log.d("trans",vehicle_transmission)


        back= findViewById((R.id.back))
        title = findViewById(R.id.registration_no)
        model_make =findViewById(R.id.model_name)
        model_name =findViewById(R.id.make_name)
        model_type =findViewById(R.id.type)
        model_transmission =findViewById(R.id.transmission_name)

        back.setOnClickListener {
            onBackPressed()
        }

        title.text = vehicleregno
        model_make.text = vehicle_make
        model_name.text = vehicle_model
        model_type.text = vehicle_fuel_type
        model_transmission.text = vehicle_transmission

    }
}