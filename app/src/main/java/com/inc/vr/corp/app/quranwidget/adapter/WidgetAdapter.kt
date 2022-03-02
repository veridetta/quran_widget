package com.inc.vr.corp.app.quranwidget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inc.vr.corp.app.quranwidget.R
import com.inc.vr.corp.app.quranwidget.model.main.ModelAyat

import kotlinx.android.synthetic.main.list_item_ayat.view.*
import java.util.*

/**
 * Created by Azhar Rivaldi on 10-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

class WidgetAdapter : RecyclerView.Adapter<WidgetAdapter.ViewHolder>() {
    private val modelAyatList = ArrayList<ModelAyat>()

    fun setAdapter(items: ArrayList<ModelAyat>) {
        modelAyatList.clear()
        modelAyatList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_ayat,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelAyatList[position]
        val rnds = (0..itemCount).random()

        if (position === rnds) {
            holder.tvNomorAyat.text = data.nomor
            holder.tvArabic.text = data.arab
            holder.tvTerjemahan.text = data.indo
        } else {
            //set unselected here
        }
    }

    override fun getItemCount(): Int {
        return modelAyatList.size
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