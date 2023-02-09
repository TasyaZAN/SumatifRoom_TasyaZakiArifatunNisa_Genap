package com.example.sumatifroom_tasyazakiarifatunnisa_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room.Constant
import com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room.codepelita
import com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { codepelita(this) }
   lateinit var barangAdapter: barangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pindah()
        setupRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        loadbarang()
    }
    fun loadbarang(){
        CoroutineScope(Dispatchers.IO).launch {
            val barangs = db.tbBarang().getbarangs()
            Log.d("MainActivity","dbResponse:$barangs")
            withContext(Dispatchers.Main){
                barangAdapter.setData(barangs)
            }
        }
    }

     fun pindah() {
        btn_input.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)}}

    fun intentEdit(barangId: Int,intentType: Int){
        startActivity(Intent(applicationContext,EditActivity::class.java)
            .putExtra("intent_id",barangId)
            .putExtra("intent_type",intentType)
        )
    }



            private fun setupRecyclerView() {
                barangAdapter = barangAdapter(arrayListOf(),object  : barangAdapter.OnAdapterListener{
                    override fun onClick(tbBarang: tb_barang) {
                        //Toast.makeText(applicationContext,tbBarang.NAMABARANG,Toast.LENGTH_SHORT).show()
                        intentEdit(tbBarang.ID,Constant.TYPE_READ)

                    }

                    override fun onUpdate(tbBarang: tb_barang) {
                        intentEdit(tbBarang.ID,Constant.TYPE_UPDATE)
                    }

                    override fun onDelete(tbBarang: tb_barang) {
                        deleteDialog(tbBarang)
                    }
                })
                id_recyclerView.apply {
                    layoutManager = LinearLayoutManager(applicationContext)
                    adapter = barangAdapter
                }
            }
    private fun deleteDialog(tbBarang: tb_barang){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin hapus ${tbBarang.NAMABARANG}?")
            setNegativeButton("Batal") {dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") {dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbBarang().Deletetb_barang(tbBarang)
                    loadbarang()
                }
            }
        }
        alertDialog.show()
    }

        }
