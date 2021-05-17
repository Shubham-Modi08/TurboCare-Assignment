package com.shubham.turbocare_assignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.shubham.turbocare_assignment.R

class SplashScreen : AppCompatActivity() {


    private  lateinit var topAnimation:Animation
    private  lateinit var bottomAnimation:Animation

    private lateinit var imageView: ImageView
    private lateinit var title:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide the status bar
//        supportActionBar?.hide()
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)



        //animations


        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        imageView = findViewById(R.id.logo)
        title = findViewById(R.id.title)


        imageView.animation = topAnimation
        title.animation = bottomAnimation



        Handler().postDelayed({ run{

            val i = Intent(this@SplashScreen,
                VehicleList::class.java)
            startActivity(i)
            finish()

        } },2500)
    }
}