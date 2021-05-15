package com.shubham.turbocare_assignment

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SelectVehicleMake : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vehicle_make)
        val vehicle_selected = intent.getStringExtra("vehicle_type")
        Log.d("data2",vehicle_selected.toString())
    }
}