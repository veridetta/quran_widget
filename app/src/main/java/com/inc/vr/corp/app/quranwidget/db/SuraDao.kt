package com.inc.vr.corp.app.quranwidget.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SuraDao {
    @Insert
    suspend fun insertAll(sura: List<Sura>)
    @Query("SELECT * FROM sura WHERE id = :id")
    suspend fun getAll(id : Int): List<Sura>
}