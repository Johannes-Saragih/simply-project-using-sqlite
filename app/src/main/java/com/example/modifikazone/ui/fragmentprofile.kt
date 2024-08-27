package com.example.modifikazone.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.modifikazone.R
import com.example.modifikazone.user.DataHelper
import com.google.android.material.appbar.MaterialToolbar

class fragmentprofile : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_fragmentprofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialized(view)
    }


    private fun initialized(v: View) {
        val db = DataHelper(v.context)

        //ini perintah button hapus maka data yang udah dibuat bakal dihapus dan akan kembali ke ActivityLogin
        view?.findViewById<Button>(R.id.btndelete)?.setOnClickListener {

            val idUser = activity?.intent!!.getStringExtra("iduser").toString()
            db.delete(idUser.toInt())

            val intent = Intent(this@fragmentprofile.requireContext(), ActivityLogin::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        view?.findViewById<Button>(R.id.btnupdate)?.setOnClickListener {

            //ini perintah button update
            val bundle = Bundle()
            val idUser = activity?.intent!!.getStringExtra("iduser").toString()
            val intent = Intent(this@fragmentprofile.requireContext(), UpdateActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            bundle.putString("iduser", idUser.toString())
            intent.putExtras(bundle)
            startActivity(intent)
        }

        view?.findViewById<MaterialToolbar>(R.id.topbackpf)?.setOnClickListener {

            //ini perintah kembali keberanda activity
            val intent = Intent(this@fragmentprofile.requireContext(),BerandaActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        //ini buat nampilin data
        val tvNama = view?.findViewById<TextView>(R.id.isinama)
        val tvemail = view?.findViewById<TextView>(R.id.isiemail)
        val tvPhone = view?.findViewById<TextView>(R.id.isinohp)

        val idUser = activity?.intent!!.getStringExtra("iduser").toString()
        val profile = db.profile(idUser.toInt())

        if (tvNama != null) {
            tvNama.text = profile[0]
        }
        if (tvemail != null) {
            tvemail.text = profile[1]
        }
        if (tvPhone != null) {
            tvPhone.text = profile[2]
        }
    }
}


