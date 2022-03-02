package com.inc.vr.corp.app.quranwidget.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IndoDaoAll {
    @Insert
    suspend fun insertAll(indo: List<Indo>)
    @Query("SELECT * FROM quran_indonesia WHERE sura=:sura")
    suspend fun getAll(sura:Int): List<Indo>
}