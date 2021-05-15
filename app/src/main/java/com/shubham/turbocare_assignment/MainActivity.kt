package com.shubham.turbocare_assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var title:TextView
    private lateinit var registrationNo: EditText
    private lateinit var nextbutton:Button
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.hide()

        title = findViewById(R.id.title)
        registrationNo = findViewById(R.id.edit_text_vehicle)
        title.text = (getString(R.string.create_profile))
        nextbutton = findViewById(R.id.next)
        radioButton1 = findViewById(R.id.two_wheeler)
        radioButton2 = findViewById(R.id.four_wheeler)




        nextbutton.setOnClickListener {

            if(noErrors()){
                if(radioButton1.isChecked() && radioButton2.isChecked()){
                    intent = Intent(applicationContext, SelectVehicleMake::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText( this,"Please Select 2 or 4 Wheeler",Toast.LENGTH_SHORT).show()
                }


            }
        }




    }

    private fun noErrors():Boolean {
        var noError = 0

        val vehicle_registration_no = registrationNo.text.toString()
        val registrationNoPattern = Regex("^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$")

        if(vehicle_registration_no.isBlank())
        {
            registrationNo.error = "Filed Missing"
        }
        else if( !vehicle_registration_no.trim().matches(registrationNoPattern) ){
            registrationNo.error = "Invalid Pattern"
        }
        else{
            noError++
        }



        return noError == 1
    }



}


