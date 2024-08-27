package com.example.modifikazone.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.modifikazone.R
import com.example.modifikazone.user.DataHelper
import com.example.modifikazone.user.User
import com.google.android.material.appbar.MaterialToolbar

class ActivityRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val namalengkap = findViewById<EditText>(R.id.edtnamalengkap)
        val nomorhp = findViewById<EditText>(R.id.edtnomorhp)
        val email = findViewById<EditText>(R.id.edtemail)
        val password = findViewById<EditText>(R.id.et_sandi)
        val button = findViewById<Button>(R.id.btn_daftar)
        val btnbackhs = findViewById<MaterialToolbar>(R.id.topbackres)


        //Kembali ke ActivityLogin
        btnbackhs.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
        }

        //melakukan registrasi, kalau udah berhasil dibalikin ke ActivityLogin buat masukin data yang udah didaftar
        button.setOnClickListener {
            if (namalengkap.text.toString().isNotEmpty() and
                password.text.toString().isNotEmpty() and
                email.text.toString().isNotEmpty() and
                nomorhp.text.toString().isNotEmpty()
            ){
                val user  = User(namalengkap.text.toString(),email.text.toString(),nomorhp.text.toString(),password.text.toString())
                val db = DataHelper(this)
                db.insertUser(user)
                val intent = Intent(this, ActivityLogin::class.java)
                startActivity(intent)
            }
            else {

                //toast topup yang menggunakan layout
                val dialogBinding = layoutInflater.inflate(R.layout.activity_completedata,null)
                val myDialog = Dialog (this)

                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()
            }
        }
    }
}