package com.shubham.turbocare_assignment.activities

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.shubham.turbocare_assignment.R
import com.shubham.turbocare_assignment.adapters.VlAdapter
import com.shubham.turbocare_assignment.adapters.VmAdapter
import com.shubham.turbocare_assignment.database.VehicleDatabase
import com.shubham.turbocare_assignment.database.VehicleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class VehicleList : AppCompatActivity(),VlAdapter.MyOnClickListener {


    private lateinit var title: TextView
    private lateinit var addVehicle: Button
    private lateinit var rcv: RecyclerView
    private lateinit var VlAdapter: VlAdapter
    private var list: ArrayList<VehicleEntity> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_list)

        addVehicle = findViewById(R.id.add_vehicle)
        title = findViewById(R.id.title)
        title.text = getString(R.string.vehicle_list)
        initView()

        addVehicle.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }

//        val list = ArrayList<VehicleEntity>()
        CoroutineScope(IO).launch {


            val db = Room.databaseBuilder(this@VehicleList, VehicleDatabase::class.java,"vehicle_details-db").build()

            val vehicleEntity:List<VehicleEntity>? = db.getVehicleDao().getMyVehicles()
            Log.d("data1", vehicleEntity?.get(0).toString())
            db.close()
            vehicleEntity?.forEach {
                list.add(it)
            }
            Log.d("list",list.toString())


        }

        VlAdapter = VlAdapter(list,this)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = VlAdapter


    }

    private fun initView() {
        rcv = findViewById(R.id.recycler_my_vehicle)
    }



    override fun OnClick(position: Int) {
        intent = Intent(applicationContext, VehicleDetails::class.java)
        val item = list[position]
        intent.putExtra("vehicle_make", item.Vehicle_make)
        intent.putExtra("Registration_no", item.Reg_no)
        intent.putExtra("vehicle_model", item.Vehicle_model)
        intent.putExtra("vehicle_fuel_type", item.Vehicle_fuel_type)
        intent.putExtra("vehicle_transmission", item.Vehicle_transmission)
        startActivity(intent)
    }
}