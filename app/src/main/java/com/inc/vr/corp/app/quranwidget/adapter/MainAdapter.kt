package com.inc.vr.corp.app.quranwidget.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.inc.vr.corp.app.quranwidget.DetailActivity
import com.inc.vr.corp.app.quranwidget.R
import com.inc.vr.corp.app.quranwidget.db.Sura
import com.inc.vr.corp.app.quranwidget.db.SuraNew
import com.inc.vr.corp.app.quranwidget.db.SuraStr
import kotlinx.android.synthetic.main.list_item_surah.view.*
import java.io.Serializable
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

class MainAdapter(private val mContext: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val modelSurahList = ArrayList<SuraNew>()
    var surahListFilter: ArrayList<SuraNew> = ArrayList()

    fun setAdapter(items: List<SuraNew>) {
        modelSurahList.clear()
        modelSurahList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_surah, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelSurahList[position]

        holder.tvNumber.text = data.id.toString()
        holder.tvName.text = data.asma
        holder.tvAyat.text = data.nama
        holder.tvInfo.text = data.type + " - " + data.ayat + " Ayat "
        holder.cvSurah.setOnClickListener {
            val intent = Intent(mContext, DetailActivity::class.java)
            intent.putExtra("nomor",  data.nomor)
            intent.putExtra("asma",  data.asma)
            intent.putExtra("nama",  data.nama)
            intent.putExtra("type",  data.type)
            intent.putExtra("ayat",  data.ayat)
            intent.putExtra("arti",  data.arti)
            intent.putExtra("audio",  data.audio)
            intent.putExtra("keterangan",  data.keterangan)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelSurahList.size
    }

    //Class Holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvSurah: CardView
        var tvNumber: TextView
        var tvAyat: TextView
        var tvInfo: TextView
        var tvName: TextView

        init {
            cvSurah = itemView.cvSurah
            tvNumber = itemView.tvNumber
            tvAyat = itemView.tvAyat
            tvInfo = itemView.tvInfo
            tvName = itemView.tvName
        }
    }
    fun getFilter(query: String?): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) surahListFilter = modelSurahList else {
                    val filteredList = ArrayList<SuraNew>()
                    modelSurahList
                            .filter {
                                (it.nama.contains(constraint!!)) or
                                        (it.nama.contains(constraint))

                            }
                            .forEach { filteredList.add(it) }
                    surahListFilter = filteredList

                }
                return FilterResults().apply { values = surahListFilter }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                surahListFilter = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<SuraNew>
                notifyDataSetChanged()
            }
        }
    }

}

