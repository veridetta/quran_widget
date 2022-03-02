package com.inc.vr.corp.app.quranwidget.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArabicDao {
    @Insert
    suspend fun insertAll(arabic: List<Arabic>)
    @Query("SELECT * FROM quran_arabic WHERE aya = :aya and sura =:sura")
    suspend fun getAll(aya : Int, sura:Int): List<Arabic>
}