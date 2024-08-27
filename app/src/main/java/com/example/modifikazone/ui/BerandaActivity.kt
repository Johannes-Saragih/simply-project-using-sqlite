package com.example.modifikazone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.modifikazone.*

class BerandaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val repairbtn = findViewById<CardView>(R.id.cardrepair)
        val shopbtn = findViewById<CardView>(R.id.cardshop)
        val profilebtn = findViewById<CardView>(R.id.cardprofile)
        val logoutbtn = findViewById<Button>(R.id.btnlogout)

        val idUser = intent!!.getStringExtra("iduser").toString()

        //Button Keluar diarahin ke ActivityLogin
        logoutbtn.setOnClickListener {
            val intent = Intent (this, ActivityLogin::class.java)
            startActivity(intent)
        }

        //Login Fragment Parts itu tempat buat nampilin datanya
        //Button Order bakal diarahin ke fragmentparts yang dimana itu nampilin orderan yang udah didaftar di WorkshopActivitiy
        shopbtn.setOnClickListener{
            val bundle = Bundle()
            val intent = Intent (this, fragmentparts::class.java)
            bundle.putString("iduser", idUser)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        //WorkshopActivity itu tempat buat melakukan pendaftaran layanan bengkel
        //Button Repair bakal diarahin ke WorkshopActivity yang dimana itu buat melakukan pendaftaran layanan bengkel
        repairbtn.setOnClickListener {
            val bundle = Bundle()
            val intent = Intent (this, WorkshopActivity::class.java)
            bundle.putString("iduser", idUser)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        //Profile itu tempat nampilin akun/data user
        //Button Profil diarahin ke fragmentprofile
        profilebtn.setOnClickListener {
            val homeFragment = fragmentprofile()
            val fragment: Fragment? =
                supportFragmentManager.findFragmentByTag(fragmentprofile::class.java.simpleName)

            if (fragment !is fragmentprofile) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.beranda, homeFragment, fragmentprofile::class.java.simpleName)
                    .commit()
            }
            profilebtn.visibility = View.GONE
        }
    }
}