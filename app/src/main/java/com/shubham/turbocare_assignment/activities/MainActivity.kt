package com.shubham.turbocare_assignment.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.shubham.turbocare_assignment.R


class MainActivity : AppCompatActivity() {

    private lateinit var back: ImageView
    private lateinit var title:TextView
    private lateinit var registrationNo: EditText
    private lateinit var nextbutton:Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var dataselected:String
    private lateinit var vehicleregno:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar?.hide()
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        back= findViewById((R.id.back))
        title = findViewById(R.id.title)
        registrationNo = findViewById(R.id.edit_text_vehicle)
        title.text = (getString(R.string.create_profile))
        nextbutton = findViewById(R.id.next)
        radioGroup = findViewById(R.id.radio_group)



        back.setOnClickListener {
            onBackPressed()
        }


        //Storing the data from the radio button
        radioGroup.setOnCheckedChangeListener { rGroup, checkedId ->
            val radioButtonID = radioGroup.checkedRadioButtonId
            val radioButton = radioGroup.findViewById<View>(radioButtonID)
            val idx = radioGroup.indexOfChild(radioButton)
            val r = radioGroup.getChildAt(idx) as RadioButton
            val selectedText = r.text.toString()
            if(selectedText == "2 WHEELER"){
                dataselected = "2w"
            }else{
                dataselected = "4w"
            }
        }


        nextbutton.setOnClickListener {

                if(noErrors()) {
                    val id: Int = radioGroup.checkedRadioButtonId
                    if (id != -1) {
                        intent = Intent(applicationContext, SelectVehicleMake::class.java)
                        vehicleregno = registrationNo.getText().toString()
                        intent.putExtra("Registration_no", vehicleregno)
                        intent.putExtra("vehicle_type", dataselected)
                        startActivity(intent)
                    }
                    else Toast.makeText(applicationContext, "Please Select 2 or 4 Wheeler", android.widget.Toast.LENGTH_SHORT).show()

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


