package com.inc.vr.corp.app.quranwidget.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArabicDaoAll {
    @Insert
    suspend fun insertAll(arabic: List<Arabic>)
    @Query("SELECT * FROM quran_arabic where sura=:sura ")
    suspend fun getAll(sura:Int): List<Arabic>
}