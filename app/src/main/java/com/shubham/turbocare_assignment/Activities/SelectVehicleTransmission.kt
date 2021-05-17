package com.shubham.turbocare_assignment.Activities

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.shubham.turbocare_assignment.Adapters.VtAdapter
import com.shubham.turbocare_assignment.R
import com.shubham.turbocare_assignment.database.VehicleDatabase
import com.shubham.turbocare_assignment.database.VehicleEntity

class SelectVehicleTransmission : AppCompatActivity(),VtAdapter.MyOnClickListener {

    private lateinit var title: TextView
    private lateinit var back: ImageView
    private lateinit var rcv: RecyclerView
    private lateinit var type_selected: String
    private lateinit var vehicleregno:String
    private lateinit var vehicle_make: String
    private lateinit var vehicle_model: String
    private lateinit var vehicle_fuel_type: String
    private lateinit var vehicle_transmission: String
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
        vehicle_transmission =  list[position]

        val vehicleEntity = VehicleEntity(
            vehicleregno,vehicle_make,vehicle_model,vehicle_fuel_type,vehicle_transmission
        )

        val async = DBASyncTask(this,vehicleEntity,1).execute()
        val result = async.get()
        Log.d("vehicle_data",result.toString())
        if(result)
        {
            Toast.makeText(applicationContext, "Vehicle Details Saved",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Some Error Occurred",Toast.LENGTH_SHORT).show()
        }

        startActivity(intent)
    }


    class DBASyncTask(val context: Context, private val VehicleEntity : VehicleEntity, private val mode:Int) : AsyncTask<Void, Void, Boolean>(){

        private val db = Room.databaseBuilder(context, VehicleDatabase::class.java,"vehicle_details-db").fallbackToDestructiveMigration().build()


        override fun doInBackground(vararg params: Void?): Boolean {

            when(mode)
            {
                1-> {
                    db.getVehicleDao().insertVehicleDetails(VehicleEntity)
                    db.close()
                    return true
                }
            }
            return false
        }
    }
}