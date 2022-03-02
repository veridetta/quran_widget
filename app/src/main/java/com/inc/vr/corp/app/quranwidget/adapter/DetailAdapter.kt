package com.inc.vr.corp.app.quranwidget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inc.vr.corp.app.quranwidget.R
import com.inc.vr.corp.app.quranwidget.db.Arabic
import com.inc.vr.corp.app.quranwidget.db.ArabicIndo
import com.inc.vr.corp.app.quranwidget.db.Indo
import com.inc.vr.corp.app.quranwidget.model.main.ModelAyat

import kotlinx.android.synthetic.main.list_item_ayat.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Azhar Rivaldi on 10-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    private val modelAyatListArab = ArrayList<Arabic>()
    private val modelAyatListIndo= ArrayList<Indo>()

    fun setAdapter(items: ArrayList<Arabic>, item2:ArrayList<Indo>) {
        modelAyatListArab.clear()
        modelAyatListArab.addAll(items)
        modelAyatListIndo.clear()
        modelAyatListIndo.addAll(item2)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_ayat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataArab = modelAyatListArab[position]
        val dataIndo = modelAyatListIndo[position]

        holder.tvNomorAyat.text = dataArab.aya.toString()
        holder.tvArabic.text = dataArab.text
        holder.tvTerjemahan.text = dataIndo.text
    }

    override fun getItemCount(): Int {
        return modelAyatListArab.size
    }

    //Class Holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNomorAyat: TextView
        var tvArabic: TextView
        var tvTerjemahan: TextView

        init {
            tvNomorAyat = itemView.tvNomorAyat
            tvArabic = itemView.tvArabic
            tvTerjemahan = itemView.tvTerjemahan
        }
    }
}