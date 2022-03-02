package com.inc.vr.corp.app.quranwidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val actionBar = supportActionBar
        actionBar!!.hide()
    }
}