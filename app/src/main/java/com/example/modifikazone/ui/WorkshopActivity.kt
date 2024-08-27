package com.example.modifikazone.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import com.example.modifikazone.R
import com.example.modifikazone.user.DataHelper
import com.example.modifikazone.user.Jadwal
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.*

class WorkshopActivity : AppCompatActivity() {

    private lateinit var tvdt: TextView
    private lateinit var plat : EditText;
    private lateinit var namaker : EditText;
    private lateinit var alamat : EditText;
    private lateinit var Dealer : AutoCompleteTextView;
    private lateinit var Layanan : AutoCompleteTextView;
    private val loadingbar : Long = 3000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workshop)

        val db = DataHelper(this)

        val idUser = intent!!.getStringExtra("iduser").toString()

        plat = findViewById(R.id.edtplat)
        namaker = findViewById(R.id.edtmobil)
        alamat = findViewById(R.id.edtalamat)
        val btnbackws = findViewById<MaterialToolbar>(R.id.topbackws)
        val next = findViewById<Button>(R.id.btnnext)
        val btndt = findViewById<Button>(R.id.btndate)
        tvdt = findViewById(R.id.tvdate)

        val myCalendar = Calendar.getInstance()

        Dealer = findViewById(R.id.Dealer)
        Layanan = findViewById(R.id.Layanan)

        //ini untuk isian dari edittext Layanan dan Dealer
        val service = listOf("Home Service", "Booking Service")
        val dealer = listOf("AUTO2000 Kembangan", "AUTO2000 Daan Mogot",
            "AUTO2000 Slipi", "AUTO2000 Sudirman")


        //ini buat narik data listof sama layout buat nampilin datanya
        val adpater = ArrayAdapter(this, R.layout.dropdownmenu, service)
        Layanan.setAdapter(adpater)
        val adpater1 = ArrayAdapter(this, R.layout.dropdownmenu, dealer)
        Dealer.setAdapter(adpater1)

        //ini untuk tanggal
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayofmonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayofmonth)
            updateLable(myCalendar)
        }

        //ini kalau button date dipencet bakal muncul kalender
        btndt.setOnClickListener {
            DatePickerDialog(this, datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        //activitas diarahin kefragmentparts
        next.setOnClickListener{

            //data kalau berisi maka langsung pindah ke fragment parts
            if (plat.text.toString().isNotEmpty() and namaker.text.toString().isNotEmpty() and alamat.text.toString().isNotEmpty()
            and Layanan.text.isNotEmpty() and Dealer.text.isNotEmpty()){

                val dialogBinding = layoutInflater.inflate(R.layout.activity_progressbar,null)

                val myDialog = Dialog (this)

                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()
                Handler().postDelayed({
                    val jadwal = Jadwal(plat.text.toString(),namaker.text.toString(),getDate(myCalendar),alamat.text.toString(),Dealer.text.toString(),Layanan.text.toString())
                    db.insertJadwal(jadwal,idUser)
                    val intent = Intent(this, BerandaActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("iduser", idUser)
                    intent.putExtras(bundle)
                    startActivity(intent)
                },loadingbar)
            }

            //ini kalau ada beberapa data yang kosong maka akan keluar notifikasi silahhkan lengkapin data
            else {
                val dialogBinding = layoutInflater.inflate(R.layout.activity_completedata,null)

                val myDialog = Dialog (this)

                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()
            }
        }

        //ini aktivitas diarahin ke BerandaActivity
        btnbackws.setOnClickListener {
            val intent = Intent(this, BerandaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateLable(myCalendar: Calendar){
        val myformat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myformat, Locale.UK)
        tvdt.text = sdf.format(myCalendar.time)
    }

    private fun getDate(myCalendar: Calendar): String{
        val myformat = "yyyy/MM/dd"
        val sdf = SimpleDateFormat(myformat, Locale.UK)
        return sdf.format(myCalendar.time)
    }
}