package com.inc.vr.corp.app.quranwidget.widget

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast
import com.inc.vr.corp.app.quranwidget.MainActivity
import com.inc.vr.corp.app.quranwidget.R
import com.inc.vr.corp.app.quranwidget.Share_Activity
import com.inc.vr.corp.app.quranwidget.db.AppDB
import com.inc.vr.corp.app.quranwidget.viewmodel.SurahViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray


/**
 * Implementation of App Widget functionality.
 */
lateinit var surahViewModel: SurahViewModel
lateinit var jsonArray : JSONArray
lateinit var arab :String
lateinit var indo : String
lateinit var latin :String
var rnds = 0
class QuranWidget : AppWidgetProvider() {
    fun updateAppWidget(context: Context,
                        appWidgetManager: AppWidgetManager,
                        appWidgetId: Int) {

        //Retrieve the current time//
        // val timeString = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date())
        val views = RemoteViews(context.packageName, R.layout.quran_widget)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                rnds = rd(1, 115)
                val listSura = AppDB.getDatabase(context).suraDao().getAll(rnds);
                val total_aya: String = listSura.get(0).ayas.toString()
                val rndsAya = rd(1, (total_aya.toInt() + 1))
                val list = AppDB.getDatabase(context).arabicDao().getAll(rndsAya,rnds);
                val listIndo = AppDB.getDatabase(context).indoDao().getAll(rndsAya,rnds);
                arab = list.get(0).text
                indo = listIndo.get(0).text
                val suraArab = "( " + listSura.get(0).name_arabic + " " + listSura.get(0).id + " : " + rndsAya + " )"
                val suraIndo = "( " + listSura.get(0).name_indonesia + " " + listSura.get(0).id + " : " + rndsAya + " )"
                views.setTextViewText(R.id.tvArabic, arab)
                views.setTextViewText(R.id.tvTerjemahan, indo)
                views.setTextViewText(R.id.tvSUraArab, suraArab)
                views.setTextViewText(R.id.tvSuraIndo, suraIndo)

                val intentUpdate = Intent(context, QuranWidget::class.java)
                intentUpdate.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                //Update the current widget instance only, by creating an array that contains the widget’s unique ID//
                val idArray = intArrayOf(appWidgetId)
                intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray)
                //Wrap the intent as a PendingIntent, using PendingIntent.getBroadcast()//
                val pendingUpdate = PendingIntent.getBroadcast(
                        context, appWidgetId, intentUpdate,
                        PendingIntent.FLAG_UPDATE_CURRENT)
                //Send the pending intent in response to the user tapping the ‘Update’ TextView//
                views.setOnClickPendingIntent(R.id.update, pendingUpdate)
                val intent = Intent(context, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
                views.setOnClickPendingIntent(R.id.buka, pendingIntent)
                val intentt = Intent(context, Share_Activity::class.java)
                intentt.putExtra("arab", arab)
                intentt.putExtra("indo", indo)
                intentt.putExtra("suraARab", suraArab)
                intentt.putExtra("suraIndo", suraIndo)
                val pendingIntentt = PendingIntent.getActivity(context, 0, intentt, FLAG_UPDATE_CURRENT)

                views.setOnClickPendingIntent(R.id.share, pendingIntentt)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            } catch (t: Throwable) {

            }
        }
    }


    fun rd(start: Int, end: Int): Int {
        val randomInteger = (start..end).shuffled().first()
        return randomInteger
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
            Toast.makeText(context, "Widget has been updated! ", Toast.LENGTH_SHORT).show()
        }
    }
}

