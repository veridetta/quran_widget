package com.inc.vr.corp.app.quranwidget.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

class SuraStr : Serializable {
        @SerializedName("id")
        lateinit var id: String
        @SerializedName("index")
        lateinit var index: String
        @SerializedName("name_indonesia")
        lateinit var name_indonesia: String

        @SerializedName("name_arabic")
        lateinit var name_arabic: String

        @SerializedName("name_english")
        lateinit var name_english: String

        @SerializedName("start")
        lateinit var start: String

        @SerializedName("ayas")
        lateinit var ayas: String

        @SerializedName("type")
        lateinit var type: String

        @SerializedName("order")
        lateinit var order: String

        @SerializedName("rukus")
        lateinit var rukus: String
}