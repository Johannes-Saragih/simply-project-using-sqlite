package com.example.modifikazone.user

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DataHelper (var context: Context): SQLiteOpenHelper(context, "your-database-name", null,1) {
    private var db = this.writableDatabase

    companion object{
        private const val TABLE_NAME = "user"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUser = "CREATE TABLE $TABLE_NAME ( "+
                " id_user INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nama VARCHAR(50), " +
                " email VARCHAR(50), "+
                " nomor VARCHAR(15), " +
                " password VARCHAR(50)); "

        val createTableJadwal   = " CREATE TABLE jadwal ( "+
                " id_jadwal INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " id_user INTEGER, " +
                " noplat VARCHAR(50), "+
                " kendaraan VARCHAR(50), "+
                " kunjungan DATETIME, " +
                " alamat VARCHAR(100)," +
                " dealer VARCHAR(100)," +
                " service VARCHAR(100)," +
                " FOREIGN KEY(id_user) references user(id_user));"

        val insertdatauser = "INSERT INTO user (nama,email,nomor,password)VALUES ('ADMIN','admin','08786991286','admin'); "

        db?.execSQL(createTableUser)
        db?.execSQL(createTableJadwal)
        db?.execSQL(insertdatauser)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertUser (user: User) {
        val cv = ContentValues()
        cv.put("nama", user.nama)
        cv.put("nomor", user.nomor)
        cv.put("email",user.email)
        cv.put("password",user.password)
        val result = db.insert("user", null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "ADD ACCOUNT FAILED", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "ADD ACCOUNT SUCCESS", Toast.LENGTH_SHORT).show()
    }

    fun updateUser (user: User, id_user: String) {
            val cv = ContentValues()
            cv.put("nama", user.nama)
            cv.put("nomor", user.nomor)
            cv.put("email",user.email)
            cv.put("password",user.password)
            val result = db.update("user", cv,"id_user=?", arrayOf(id_user))
            println(result)
                        if (result > 0)
                            Toast.makeText(context, "UPDATE ACCOUNT SUCCESS", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "UPDATE ACCOUNT FAILED", Toast.LENGTH_SHORT).show()
    }

    fun insertJadwal (jadwal: Jadwal, idUser: String?) {
        val cv = ContentValues()
        cv.put("noplat", jadwal.noplat)
        cv.put("kendaraan", jadwal.kendaraan)
        cv.put("kunjungan",jadwal.kunjungan)
        cv.put("alamat",jadwal.alamat)
        cv.put("dealer",jadwal.dealer)
        cv.put("service",jadwal.service)
        cv.put("id_user", idUser)
        val result = db.insert("jadwal", null, cv)

    }

    fun getJadwal (id: String) : Array<String> {
        val query = "SELECT * FROM jadwal WHERE id_user = '$id'"
        val rs = db.rawQuery(query, null)
        if (rs.moveToFirst()) {
            return arrayOf(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(0))
        }
        return arrayOf("","","","","","","","")
    }

    @SuppressLint("Range")
    fun checkUser(email: String, password: String): Int {
        val query = "SELECT * FROM user WHERE email='$email' AND password='$password'"
        val rs = db.rawQuery(query, null)
        if (rs.moveToFirst()) {
            val idUser = rs.getInt(rs.getColumnIndex("id_user"))
            rs.close()

            return idUser
        }
        return -1
    }

    @SuppressLint("Range")
    fun profile(id: Int) : Array<String>{
        val query = "SELECT nama,email,nomor FROM user WHERE id_user='$id'"
        val rs = db.rawQuery(query,null)
        if(rs.moveToFirst()) {
            val nama = rs.getString(rs.getColumnIndex("nama"))
            val email = rs.getString(rs.getColumnIndex("email"))
            val nomor = rs.getString(rs.getColumnIndex("nomor"))
            rs.close()
            return arrayOf(nama,email,nomor)
        }
        return arrayOf("","","","")
    }

    fun delete(id: Int){
        db?.execSQL("DELETE FROM user WHERE id_user=$id")
        db?.close()
//        if(rs.moveToFirst()) {
        }

    fun deleteJadwal(id: Int){
        db?.execSQL("DELETE FROM jadwal WHERE id_jadwal=$id")
        db?.close()
    }
}

