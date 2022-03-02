package com.inc.vr.corp.app.quranwidget

import android.content.Context
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.inc.vr.corp.app.quranwidget.db.AppDB

import kotlinx.android.synthetic.main.activity_tes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.properties.Delegates


class Tes : AppCompatActivity() {
    lateinit var jsonArray : JSONArray
    lateinit var arab :String
    lateinit var indo : String
    lateinit var latin :String
    var rnds = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)
        val context : Context = this
        GlobalScope.launch(Dispatchers.Main) {
            try {
                rnds = (1..115).random()
                val listSura = AppDB.getDatabase(context).suraDao().getAll(rnds);
                val total_aya : String = listSura.get(0).ayas.toString()
                val rndsAya = (1..(total_aya.toInt()+1)).random()
                val list = AppDB.getDatabase(context).arabicDao().getAll(rndsAya, rnds);
                val listIndo = AppDB.getDatabase(context).indoDao().getAll(rndsAya, rnds);
                arab = list.get(0).text
                indo = listIndo.get(0).text
                val sura = listSura.get(0).name_arabic
                tvArabic.setText(arab)
               tvTerjemahan.setText(indo)
                //tvArabic_ket.setText(sura)
            } catch (t: Throwable) {
                tvArabic_ket.setText(t.message)
            }
        }

    }
}