package com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class tb_barang (
        @PrimaryKey
        val ID : Int,
        val NAMABARANG : String,
        val HARGA : Int,
        val QTY : Int
         )
