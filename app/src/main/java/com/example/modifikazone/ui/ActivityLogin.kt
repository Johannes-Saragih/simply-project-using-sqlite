package com.example.modifikazone.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.modifikazone.R
import com.example.modifikazone.user.DataHelper

class ActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.edtemail)
        val password = findViewById<EditText>(R.id.edtpass)
        val btnlogin = findViewById<Button>(R.id.btnlogin)
        val txtdaftar = findViewById<TextView>(R.id.daftar)

        val db = DataHelper(this)

        //aktivitas login bakal diarahin ke BerandaActivity
        btnlogin.setOnClickListener {

            if (email.text.toString().isNotEmpty() and password.text.toString().isNotEmpty()) {
                val idUser = db.checkUser(email.text.toString(),password.text.toString())
                if (idUser>0){
                    val bundle = Bundle()
                    val intent = Intent(this, BerandaActivity::class.java)
                    bundle.putString("iduser", idUser.toString())
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
                else {
                    val dialogBinding = layoutInflater.inflate(R.layout.activity_notregistered,null)
                    val myDialog = Dialog (this)

                    myDialog.setContentView(dialogBinding)
                    myDialog.setCancelable(true)
                    myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    myDialog.show()
                }
            }
            else{
                val dialogBinding = layoutInflater.inflate(R.layout.activity_completeaccount,null)
                val myDialog = Dialog (this)

                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()
            }
        }


        //diarahin ke ActivityRegister
        txtdaftar.setOnClickListener {
            val intent = Intent(this, ActivityRegister::class.java)
            startActivity(intent)
        }
    }
}