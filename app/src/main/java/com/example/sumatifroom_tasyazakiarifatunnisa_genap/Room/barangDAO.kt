package com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room

import androidx.room.*

@Dao
interface barangDAO {
    @Insert
    fun addtb_barang (tbBarang: tb_barang)

    @Update
    fun updatetb_barang (tbBarang: tb_barang)

    @Delete
    fun Deletetb_barang (tbBarang: tb_barang)

    @Query("SELECT * FROM tb_barang")
    suspend fun getbarangs() : List<tb_barang>

    @Query("SELECT * FROM tb_barang WHERE id=:barang_id ")
    suspend fun tampil(barang_id:Int) : List<tb_barang>

    @Query("SELECT * FROM tb_barang ORDER BY NAMABARANG,ID ASC ")
    suspend fun muncul() : List<tb_barang>

}