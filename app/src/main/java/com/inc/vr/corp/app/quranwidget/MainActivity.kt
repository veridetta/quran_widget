package com.inc.vr.corp.app.quranwidget

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.inc.vr.corp.app.quranwidget.adapter.MainAdapter
import com.inc.vr.corp.app.quranwidget.db.AppDB
import com.inc.vr.corp.app.quranwidget.fragment.FragmentJadwalSholat.Companion.newInstance
import com.inc.vr.corp.app.quranwidget.viewmodel.SurahViewModel
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    var REQ_PERMISSION = 100
    var strCurrentLatitude = 0.0
    var strCurrentLongitude = 0.0
    lateinit var strCurrentLatLong: String
    lateinit var strDate: String
    lateinit var strDateNow: String
    lateinit var simpleLocation: SimpleLocation
    lateinit var mainAdapter: MainAdapter
    lateinit var progressDialog: ProgressDialog
    lateinit var surahViewModel: SurahViewModel
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        context = this
        actionBar!!.hide()
        setInitLayout()
        setPermission()
        setLocation()
        //country_search.setOnQueryTextListener(this)
        setCurrentLocation()
        setViewModel()
    }

    private fun setInitLayout() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Sedang menampilkan data...")

        val dateNow = Calendar.getInstance().time
        strDate = DateFormat.format("EEEE", dateNow) as String
        strDateNow = DateFormat.format("d MMMM yyyy", dateNow) as String

        tvToday.setText("$strDate,")
        tvDate.setText(strDateNow)

        mainAdapter = MainAdapter(this)
        rvSurah.setHasFixedSize(true)
        rvSurah.setLayoutManager(LinearLayoutManager(this))
        rvSurah.setAdapter(mainAdapter)

        val jadwalSholat = newInstance("Jadwal Sholat")
        layoutTime.setOnClickListener {
            jadwalSholat.show(
                    supportFragmentManager, jadwalSholat.tag
            )
        }

        layoutMosque.setOnClickListener {
            startActivity(
                    Intent(
                            this@MainActivity,
                            InfoActivity::class.java
                    )
            )
        }
    }

    private fun setLocation() {
        simpleLocation = SimpleLocation(this)
        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(this)
        }

        //get location
        strCurrentLatitude = simpleLocation.latitude
        strCurrentLongitude = simpleLocation.longitude

        //set location lat long
        strCurrentLatLong = "$strCurrentLatitude,$strCurrentLongitude"
    }

    private fun setCurrentLocation() {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addressList = geocoder.getFromLocation(strCurrentLatitude, strCurrentLongitude, 1)
            if (addressList != null && addressList.size > 0) {
                val strCurrentLocation = addressList[0].locality
                tvLocation.text = strCurrentLocation
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun setViewModel() {
        /*progressDialog.show()
        surahViewModel = ViewModelProvider(this, NewInstanceFactory()).get(SurahViewModel::class.java)
        surahViewModel.setSurah()
        surahViewModel.getSurah()
                .observe(this, { modelSurah: ArrayList<ModelSurah> ->
                    if (modelSurah.size != 0) {
                        mainAdapter.setAdapter(modelSurah)
                        progressDialog.dismiss()
                    } else {
                        Toast.makeText(this, "Data Tidak Ditemukan!", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }
                    progressDialog.dismiss()
                })*/
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val listSura = AppDB.getDatabase(context).suraDaoAll().getAll();
                mainAdapter.setAdapter(listSura)
            } catch (t: Throwable) {

            }
        }
    }

    private fun setPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQ_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_PERMISSION && resultCode == RESULT_OK) {

            //load data
            setViewModel()
        }
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        mainAdapter.getFilter(query)
        return false
    }

     override fun onQueryTextChange(newText: String?): Boolean {
         mainAdapter.getFilter(newText)
        return false
    }
}