package com.inc.vr.corp.app.quranwidget.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "surat")
data class SuraNew(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @NotNull
        @ColumnInfo(name = "arti") val arti: String,
        @NotNull
        @ColumnInfo(name = "asma") val asma: String,
        @NotNull
        @ColumnInfo(name = "ayat") val ayat: Int,
        @NotNull
        @ColumnInfo(name = "nama") val nama: String,
        @NotNull
        @ColumnInfo(name = "type") val type: String,
        @NotNull
        @ColumnInfo(name = "urut") val urut: Int,
        @NotNull
        @ColumnInfo(name = "audio") val audio: String,
        @NotNull
        @ColumnInfo(name = "nomor") val nomor: Int,
        @NotNull
        @ColumnInfo(name = "rukuk") val rukuk: Int,
        @NotNull
        @ColumnInfo(name = "keterangan") val keterangan: String
)