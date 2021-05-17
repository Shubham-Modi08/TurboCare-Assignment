package com.shubham.turbocare_assignment.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.shubham.turbocare_assignment.Adapters.VmAdapter
import com.shubham.turbocare_assignment.R


class SelectVehicleMake : AppCompatActivity(), VmAdapter.MyOnClickListener {

    private lateinit var title: TextView
    private lateinit var back: ImageView
    private lateinit var type_selected: String
    private lateinit var vehicleregno:String
    private lateinit var rcv: RecyclerView
    private lateinit var VmAdapter: VmAdapter
    private var list: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_vehicle_make)
        type_selected = intent.getStringExtra("vehicle_type")!!
        vehicleregno = intent.getStringExtra("Registration_no")!!
        Log.d("data2", type_selected)
        getData()
        initView()

        back= findViewById((R.id.back))
        title = findViewById(R.id.title)
        title.text = getString(R.string.vehicle_make)


        back.setOnClickListener {
            onBackPressed()
        }

    }




    private fun getData() {


        val url = getString(R.string.base_url)+"makes?class="+type_selected
        val requestQueue = Volley.newRequestQueue(this)
        val request = JsonArrayRequest(Request.Method.GET, url,null,
                { response ->
                    Log.d("item",response.toString())
                    for (i in 0 until response.length()){
                        list.add(response[i].toString())
                    }
                    VmAdapter = VmAdapter(list,this)
                    rcv.layoutManager = LinearLayoutManager(this)
                    rcv.adapter = VmAdapter
                }, { error ->

        })
        requestQueue.add(request)

    }

    private fun initView() {
        rcv = findViewById(R.id.recycler_view_make)
    }

    override fun OnClick(position: Int) {
        intent = Intent(applicationContext, SelectVehicleModel::class.java)
        intent.putExtra("vehicle_type", type_selected)
        intent.putExtra("vehicle_make", list[position])
        intent.putExtra("Registration_no", vehicleregno)
        startActivity(intent)
    }

}




