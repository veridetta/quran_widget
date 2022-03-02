package com.inc.vr.corp.app.quranwidget.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IndoDao {
    @Insert
    suspend fun insertAll(indo: List<Indo>)
    @Query("SELECT * FROM quran_indonesia WHERE aya=:aya and sura =:sura")
    suspend fun getAll(aya : Int, sura:Int): List<Indo>
}