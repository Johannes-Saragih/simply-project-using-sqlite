package com.example.modifikazone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.modifikazone.R
import com.example.modifikazone.user.DataHelper
import com.google.android.material.appbar.MaterialToolbar

class fragmentparts : AppCompatActivity() {

//    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
    val db = DataHelper(this)

    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentparts)
        val bundle :Bundle ?=intent.extras
        val idUser = bundle!!.getString("iduser")
        val btnbackor = findViewById<MaterialToolbar>(R.id.topbackpr)
        val txtpla = findViewById<TextView>(R.id.textplat)
        val txtker = findViewById<TextView>(R.id.textkendaraan)
        val txtala = findViewById<TextView>(R.id.textalamat)
        val txtser = findViewById<TextView>(R.id.textservice)
        val txtder = findViewById<TextView>(R.id.textdealer)
        val txtdat = findViewById<TextView>(R.id.textdate)
        val btndone = findViewById<Button>(R.id.btndone)
        val btnhapus = findViewById<Button>(R.id.btnhapus)

//        recyclerView = findViewById(R.id.recycler_view)
        val jadwal = db.getJadwal(idUser.toString())
        val plat = jadwal[0]
        val kendaraan = jadwal[1]
        val date = jadwal[2]
        val alamat= jadwal[3]
        val dealer = jadwal[4]
        val service = jadwal[5]

        txtpla.text = plat
        txtker.text = kendaraan
        txtala.text = alamat
        txtser.text = service
        txtder.text = dealer
        txtdat.text = date

        //button kembali ke BerandaActitviy
        btnbackor.setOnClickListener {
            val intent = Intent(this, BerandaActivity::class.java)
            startActivity(intent)
        }

        //button done untuk kembali ke BerandaActivity
        btndone.setOnClickListener {
            val intent = Intent(this, BerandaActivity::class.java)
            startActivity(intent)
        }

        //hapus data yang sudah diregistrasi
        btnhapus.setOnClickListener {
            db.deleteJadwal(jadwal[6].toInt())

            val intent = Intent(this, BerandaActivity::class.java)
            startActivity(intent)
        }
    }
}