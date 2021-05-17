package com.shubham.turbocare_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubham.turbocare_assignment.Adapters.Vfadapter
import com.shubham.turbocare_assignment.Adapters.VtAdapter

class SelectVehicleTransmission : AppCompatActivity(),VtAdapter.MyOnClickListener {

    private lateinit var title: TextView
    private lateinit var back: ImageView
    private lateinit var rcv: RecyclerView
    private lateinit var type_selected: String
    private lateinit var vehicleregno:String
    private lateinit var vehicle_make: String
    private lateinit var vehicle_model: String
    private lateinit var vehicle_fuel_type: String
    private lateinit var VtAdapter: VtAdapter
    private val list: ArrayList<String> =  ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vehicle_transmission)

        type_selected = intent.getStringExtra("vehicle_type")!!
        vehicle_make = intent.getStringExtra("vehicle_make")!!
        vehicleregno = intent.getStringExtra("Registration_no")!!
        vehicle_model = intent.getStringExtra("vehicle_model")!!
        vehicle_fuel_type = intent.getStringExtra("vehicle_fuel_type")!!
        getData()
        initView()


        back= findViewById((R.id.back))
        title = findViewById(R.id.title)
        title.text = getString(R.string.vehicle_transmission)


        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getData(){

        list.add("Manual")
        list.add("Automatic")

    }

    private fun initView() {
        rcv = findViewById(R.id.recycler_view_transmission)
        VtAdapter = VtAdapter(list,this)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = VtAdapter

    }

    override fun OnClick(position: Int) {
        intent = Intent(applicationContext, VehicleList::class.java)
        intent.putExtra("vehicle_type", type_selected)
        intent.putExtra("vehicle_make", list[position])
        intent.putExtra("Registration_no", vehicleregno)
        intent.putExtra("vehicle_model", list[position])
        intent.putExtra("vehicle_fuel_type", list[position])
        intent.putExtra("vehicle_transmission", list[position])
        Toast.makeText(applicationContext, "Vehicle Details Saved",Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}