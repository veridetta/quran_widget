package com.inc.vr.corp.app.quranwidget.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "quran_arabic")
data class Arabic(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @NotNull
        @ColumnInfo(name = "index") val index: Integer,
        @NotNull
        @ColumnInfo(name = "sura") val sura: Integer,
        @NotNull
        @ColumnInfo(name = "aya") val aya: Integer,
        @NotNull
        @ColumnInfo(name = "text") val text: String,
        @NotNull
        @ColumnInfo(name = "page") val page: Int,
)