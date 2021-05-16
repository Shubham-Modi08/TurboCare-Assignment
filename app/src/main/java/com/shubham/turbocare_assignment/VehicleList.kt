package com.shubham.turbocare_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class VehicleList : AppCompatActivity() {


    private lateinit var title: TextView
    private lateinit var addVehicle: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_list)

        addVehicle = findViewById(R.id.add_vehicle)
        title = findViewById(R.id.title)
        title.text = getString(R.string.vehicle_list)

        addVehicle.setOnClickListener {
            val i = Intent(this,
                MainActivity::class.java)
            startActivity(i)

        }

    }
}