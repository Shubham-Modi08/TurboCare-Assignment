package com.shubham.turbocare_assignment.Activities

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.shubham.turbocare_assignment.R
import com.shubham.turbocare_assignment.database.VehicleDatabase
import com.shubham.turbocare_assignment.database.VehicleEntity

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
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }


        class DBASyncTask(context: Context, private val vehicleEntity: VehicleEntity, private val mode:Int) : AsyncTask<Void, Void, Boolean>(){

            private val db = Room.databaseBuilder(context, VehicleDatabase::class.java,"vehicle_details-db").build()

            override fun doInBackground(vararg params: Void?): Boolean {

                return when(mode){
                    1 -> {
                        val vehicleEntity :VehicleEntity? = db.getVehicleDao().getMyVehicles(List<VehicleEntity>)
                        db.close()
                        vehicleEntity !=null
                    }
                    else -> false
                }
            }

        }



    }
}