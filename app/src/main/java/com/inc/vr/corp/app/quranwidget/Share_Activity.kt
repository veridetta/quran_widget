package com.inc.vr.corp.app.quranwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.inc.vr.corp.app.quranwidget.db.AppDB
import com.inc.vr.corp.app.quranwidget.widget.QuranWidget
import com.inc.vr.corp.app.quranwidget.widget.arab
import com.inc.vr.corp.app.quranwidget.widget.indo
import com.inc.vr.corp.app.quranwidget.widget.rnds
import kotlinx.android.synthetic.main.activity_share_.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Share_Activity : AppCompatActivity() {
    lateinit var context: Context
    var strAyat : Int = 0
    var strSurat : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_)
        val actionBar = supportActionBar
        context = this
        actionBar!!.hide()
        val intent = intent
        val arab = intent.getStringExtra("arab")
        val indo = intent.getStringExtra("indo")
        val suraArab = intent.getStringExtra("suraARab")
        val suraIndo = intent.getStringExtra("suraIndo")
        tvArabic.text= arab
        tvTerjemahan.text=indo
        tvSUraArab.text=suraArab
        tvSuraIndo.text=suraIndo
        Handler().postDelayed({
            val v: LinearLayout = ly_share
            v.isDrawingCacheEnabled = true
            v.buildDrawingCache(true)
            val saveBm = Bitmap.createBitmap(v.drawingCache)
            v.isDrawingCacheEnabled = false
            val bitmapPath = MediaStore.Images.Media.insertImage(
                v.context.contentResolver,
                saveBm, title as String, "Sumber Quran Daily WIdget"
            )
            val shareIntent: Intent

            val bmpUri = Uri.parse(bitmapPath)
            shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri)
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Download aplikasi ini secara gratis " + "https://play.google.com/store/apps/details?id=" + context
                    .getPackageName()
            )
            shareIntent.type = "image/png"
            context.startActivity(Intent.createChooser(shareIntent, "Share with"))
        }, 900)

        /*
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val listSura = AppDB.getDatabase(context).suraDao().getAll(strSurat);
                val total_aya: String = listSura.get(0).ayas.toString()
                val list = AppDB.getDatabase(context).arabicDao().getAll(strAyat);
                val listIndo = AppDB.getDatabase(context).indoDao().getAll(strAyat);
                arab = list.get(0).text
                indo = listIndo.get(0).text
                val suraArab =
                    "( " + listSura.get(0).name_arabic + " " + listSura.get(0).id + " : " + list.get(
                        0
                    ).aya + " )"
                val suraIndo =
                    "( " + listSura.get(0).name_indonesia + " " + listSura.get(0).id + " : " + list.get(
                        0
                    ).aya + " )"

            } catch (t: Throwable) {

            }

        }

         */

    }
}