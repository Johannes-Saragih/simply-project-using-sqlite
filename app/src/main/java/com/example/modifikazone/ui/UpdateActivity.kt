package com.example.modifikazone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.modifikazone.R
import com.example.modifikazone.user.DataHelper
import com.example.modifikazone.user.User
import com.google.android.material.appbar.MaterialToolbar

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val namalengkap = findViewById<EditText>(R.id.edtnamalengkapup)
        val nomorhp = findViewById<EditText>(R.id.edtnomorhpup)
        val email = findViewById<EditText>(R.id.edtemailup)
        val password = findViewById<EditText>(R.id.et_sandiup)
        val button = findViewById<Button>(R.id.btnupdate)
        val btnbackrp = findViewById<MaterialToolbar>(R.id.topbackUpdate)
        val db = DataHelper(this)

        val idUser = intent!!.getStringExtra("iduser").toString()

        //ini buat narik data yang udah ada didatabase
        val profile = db.profile(idUser.toInt())
            namalengkap.setText(profile[0])
            email.setText(profile[1])
            nomorhp.setText(profile[2])

        //ini buat perbarui kalau data sudah selesai dan bakal kembali ke fragmentprofile
        button.setOnClickListener {

            //ini kalau data berisi semua maka akan langsung intent ke fragmentprofile
            if (namalengkap.text.toString().isNotEmpty() and
                password.text.toString().isNotEmpty() and
                email.text.toString().isNotEmpty() and
                nomorhp.text.toString().isNotEmpty()
            ) {
                val user = User(namalengkap.text.toString(),
                    email.text.toString(),
                    nomorhp.text.toString(),
                    password.text.toString())

                db.updateUser(user,idUser)

                val homeFragment = fragmentprofile()
                val fragment: Fragment? =
                    supportFragmentManager.findFragmentByTag(fragmentprofile::class.java.simpleName)

                if (fragment !is fragmentprofile) {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.updateActivity, homeFragment, fragmentprofile::class.java.simpleName)
                        .commit()
                }
                button.visibility = View.GONE
            }


        //ini activitas kembali keberanda kalau gajadi melakukan update
        }
        btnbackrp.setOnClickListener {
                val intent = Intent(this, BerandaActivity::class.java)
                startActivity(intent)
            }
    }
}