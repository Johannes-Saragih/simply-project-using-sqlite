package com.example.modifikazone.user

class Jadwal {
    var noplat : String = ""
    var kendaraan : String = ""
    var kunjungan : String = ""
    var alamat : String = ""
    var dealer : String = ""
    var service : String = ""
    constructor( noplat : String , kendaraan : String, kunjungan :String, alamat :String, dealer :String, service :String){
         this.noplat = noplat
         this.kendaraan = kendaraan
        this.kunjungan = kunjungan
        this.alamat = alamat
        this.dealer = dealer
        this.service = service
     }
}
