package com.example.sumatifroom_tasyazakiarifatunnisa_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room.Constant
import com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room.codepelita
import com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
     private val db by lazy { codepelita(this) }
     private var barangId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        setupListener()

        Toast.makeText(this,barangId.toString(),Toast.LENGTH_SHORT).show()
    }
    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE ->{
                btn_update.visibility = View.GONE
            }
            Constant.TYPE_READ ->{
                btn_save.visibility = View.GONE
                btn_update.visibility =View.GONE
                getBarang()

            }
            Constant.TYPE_UPDATE->{
                btn_save.visibility=View.GONE
                getBarang()
            }
        }
    }
     fun setupListener() {
         btn_save.setOnClickListener{
             CoroutineScope(Dispatchers.IO).launch {
                 db.tbBarang().addtb_barang(
                 tb_barang (id_ID.text.toString().toInt(),id_namabarang.text.toString(),id_harga.text.toString().toInt(),id_QTY.text.toString().toInt())
                 )
                 finish()
             }
         }
         btn_update.setOnClickListener{
             CoroutineScope(Dispatchers.IO).launch {
                 db.tbBarang().updatetb_barang(
                     tb_barang (id_ID.text.toString().toInt(),id_namabarang.text.toString(),id_harga.text.toString().toInt(),id_QTY.text.toString().toInt())
                 )
                 finish()
             }
         }
     }
    fun getBarang(){
        barangId = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val barangs = db.tbBarang().tampil(barangId)[0]
            val muncul = db.tbBarang().muncul()
            val ide : String = barangs.ID.toString()
            val harga : String= barangs.HARGA.toString()
            val Qty : String = barangs.QTY.toString()
            id_namabarang.setText(barangs.NAMABARANG)
            id_ID.setText(ide)
            id_harga.setText(harga)
            id_QTY.setText(Qty)


        }
    }




    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}