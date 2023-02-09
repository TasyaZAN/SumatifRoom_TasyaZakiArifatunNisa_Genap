package com.example.sumatifroom_tasyazakiarifatunnisa_genap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.view.*
import kotlinx.android.synthetic.main.adapter_barang.view.*

class barangAdapter (private val  barangs:ArrayList<tb_barang>,private  val listener :OnAdapterListener)
    :RecyclerView.Adapter<barangAdapter.barangViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): barangViewHolder { return barangViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_barang,parent,false)
    )
    }
    override fun getItemCount() = barangs.size

    override fun onBindViewHolder(holder: barangAdapter.barangViewHolder, position: Int) {
        val barang = barangs[position]
        holder.view.id_textnamabrg.text= barang.NAMABARANG
        holder.view.id_textID.text= barang.ID.toString()
        holder.view.id_textnamabrg.setOnClickListener{
            listener.onClick(barang)
        }
        holder.view.icon_edit.setOnClickListener{
            listener.onUpdate(barang)
        }
        holder.view.icon_delete.setOnClickListener{
            listener.onDelete(barang)
        }

    }
    class barangViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<tb_barang>){
        barangs.clear()
        barangs.addAll(list)
        notifyDataSetChanged()
    }


    interface OnAdapterListener {
    fun onClick(tbBarang: tb_barang)
    fun onUpdate(tbBarang: tb_barang)
    fun onDelete(tbBarang: tb_barang)
}

}
