package com.inc.vr.corp.app.quranwidget.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SuraDaoAll {
    @Insert
    suspend fun insertAll(surat : List<SuraNew>)
    @Query("SELECT * FROM surat")
    suspend fun getAll(): List<SuraNew>
}