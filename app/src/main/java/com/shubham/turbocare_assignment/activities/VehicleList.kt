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

class VehicleList : AppCompatActivity() {


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

        val result = DBASyncTask(this,1).execute().get()
        Log.d("result", result.toString())
        //VlAdapter = VlAdapter(,this)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = VlAdapter



    }

    private fun initView() {
        rcv = findViewById(R.id.recycler_my_vehicle)
    }

    private fun vehicleList(vehicle : ArrayList<VehicleEntity>){
        list = vehicle
    }

    class DBASyncTask(context: Context, private val mode:Int) : AsyncTask<Void, Void, Boolean>(){

        val list = ArrayList<VehicleEntity>()

        private val db = Room.databaseBuilder(context, VehicleDatabase::class.java,"vehicle_details-db").build()

        override fun doInBackground(vararg params: Void?): Boolean {

            return when(mode){
                1 -> {
                    val vehicleEntity:List<VehicleEntity>? = db.getVehicleDao().getMyVehicles()
                    Log.d("data1", vehicleEntity?.get(0).toString())
                    db.close()
                    vehicleEntity?.forEach {
                        list.add(it)
                    }
                    Log.d("list",list.toString())
                    vehicleEntity != null
                }
                else -> false
            }
        }

    }
}