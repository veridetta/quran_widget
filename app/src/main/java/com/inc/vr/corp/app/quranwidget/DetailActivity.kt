package com.inc.vr.corp.app.quranwidget

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.inc.vr.corp.app.quranwidget.adapter.DetailAdapter
import com.inc.vr.corp.app.quranwidget.db.*
import com.inc.vr.corp.app.quranwidget.viewmodel.SurahViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.list_item_surah.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException


class DetailActivity : AppCompatActivity() {
    lateinit var strNomor: String
    lateinit var strNama: String
    lateinit var strAsma: String
    lateinit var strType: String
    lateinit var strAyat: String
    lateinit var strKeterangan: String
    lateinit var strAudio: String
    lateinit var strArti: String

    lateinit var detailAdapter: DetailAdapter
    lateinit var progressDialog: ProgressDialog
    lateinit var surahViewModel: SurahViewModel
    lateinit var handler: Handler
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar = supportActionBar
        actionBar!!.hide()
        context = this
        setInitLayout()
        setViewModel()
    }

    @SuppressLint("RestrictedApi")
    private fun setInitLayout() {
        //setSupportActionBar(toolbar)
        context = this
        handler = Handler()
        val intent = intent
            strNomor = intent.getIntExtra("nomor",0).toString()
            strNama = intent.getStringExtra("nama").toString()
            strAsma = intent.getStringExtra("asma").toString()
            strType = intent.getStringExtra("type").toString()
            strArti = intent.getStringExtra("arti").toString()
            strAudio = intent.getStringExtra("audio").toString()
            strAyat = intent.getIntExtra("ayat",0).toString()
            strKeterangan = intent.getStringExtra("keterangan").toString()
            fabStop.visibility = View.GONE
            fabPlay.visibility = View.VISIBLE
            val actionBar = supportActionBar
            actionBar!!.title=strNama
            //Set text
            tvTitle.text = strNama
            tvSubTitle.text = strArti
            tvInfo2.text = "$strType - $strAyat Ayat "

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) tvKet.text =
                    Html.fromHtml(strKeterangan, Html.FROM_HTML_MODE_COMPACT) else {
                tvKet.text = Html.fromHtml(strKeterangan)
            }

            val mediaPlayer = MediaPlayer()

            fabPlay.setOnClickListener {
                try {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                    mediaPlayer.setDataSource(strAudio)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                fabPlay.visibility = View.GONE
                fabStop.visibility = View.VISIBLE
            }

            fabStop.setOnClickListener {
                mediaPlayer.stop()
                mediaPlayer.reset()
                fabPlay.visibility = View.VISIBLE
                fabStop.visibility = View.GONE
            }

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Sedang menampilkan data...")

        detailAdapter = DetailAdapter()
        rvAyat.setHasFixedSize(true)
        rvAyat.layoutManager = LinearLayoutManager(this)
        rvAyat.adapter = detailAdapter
    }

    private fun setViewModel() {
        /* progressDialog.show()
        surahViewModel = ViewModelProvider(this, NewInstanceFactory()).get(SurahViewModel::class.java)
        surahViewModel.setDetailSurah(strNomor)
        surahViewModel.getDetailSurah()
                .observe(this, { modelAyat: ArrayList<ModelAyat> ->
                    if (modelAyat.size != 0) {
                        detailAdapter.setAdapter(modelAyat)
                        progressDialog.dismiss()
                    } else {
                        Toast.makeText(this, "Data Tidak Ditemukan!", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }
                    progressDialog.dismiss()
                })

         */
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val listARab = AppDB.getDatabase(context).arabDaoAll().getAll(strNomor.toInt());
                val listIndo = AppDB.getDatabase(context).indoDaoAll().getAll(strNomor.toInt());
                detailAdapter.setAdapter(listARab as ArrayList<Arabic>, listIndo as ArrayList<Indo>)
                Log.d("DetailGet",listARab.size.toString())
            } catch (t: Throwable) {

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val DETAIL_SURAH = "DETAIL_SURAH"
    }

}