package com.shubham.turbocare_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.shubham.turbocare_assignment.Adapters.Vmoadapter

class SelectVehicleModel : AppCompatActivity(),Vmoadapter.MyOnClickListener {


    private lateinit var title: TextView
    private lateinit var back: ImageView
    private lateinit var rcv: RecyclerView
    private lateinit var type_selected: String
    private lateinit var vehicleregno:String
    private lateinit var vehicle_make: String
    private lateinit var Vmoadapter: Vmoadapter
    private val list: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vehicle_model)
        type_selected = intent.getStringExtra("vehicle_type")!!
        vehicle_make = intent.getStringExtra("vehicle_make")!!
        vehicleregno = intent.getStringExtra("Registration_no")!!
        getData()
        initView()

        back= findViewById((R.id.back))
        title = findViewById(R.id.title)
        title.text = getString(R.string.vehicle_model)


        back.setOnClickListener {
            onBackPressed()
        }

    }

    private fun getData() {


        val url = getString(R.string.base_url)+"models?class="+type_selected+"&make="+vehicle_make
        val requestQueue = Volley.newRequestQueue(this)
        val request = JsonArrayRequest(
            Request.Method.GET, url,null,
            { response ->
                Log.d("item",response.toString())
                for (i in 0 until response.length()){
                    list.add(response[i].toString())
                }
                Vmoadapter = Vmoadapter(list,this)
                rcv.layoutManager = LinearLayoutManager(this)
                rcv.adapter = Vmoadapter
            }, { error ->

            })
        requestQueue.add(request)

    }

    private fun initView() {
        rcv = findViewById(R.id.recycler_view_model)
    }

    override fun OnClick(position: Int) {

        intent = Intent(applicationContext, SelectVehicleFuelType::class.java)
        intent.putExtra("vehicle_type", type_selected)
        intent.putExtra("vehicle_make", list[position])
        intent.putExtra("Registration_no", vehicleregno)
        intent.putExtra("vehicle_model", list[position])
        startActivity(intent)


    }

}