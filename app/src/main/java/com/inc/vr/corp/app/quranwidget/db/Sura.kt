package com.inc.vr.corp.app.quranwidget.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "sura")
data class Sura(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @NotNull
        @ColumnInfo(name = "index") val index: Integer,
        @NotNull
        @ColumnInfo(name = "name_indonesia") val name_indonesia: String,
        @NotNull
        @ColumnInfo(name = "name_arabic") val name_arabic: String,
        @NotNull
        @ColumnInfo(name = "name_english") val name_english: String,
        @NotNull
        @ColumnInfo(name = "start") val start: Integer,
        @NotNull
        @ColumnInfo(name = "ayas") val ayas: Integer,
        @NotNull
        @ColumnInfo(name = "type") val type: String,
        @NotNull
        @ColumnInfo(name = "order") val order: Integer,
        @NotNull
        @ColumnInfo(name = "rukus") val rukus: Integer,
)