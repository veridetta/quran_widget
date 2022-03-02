package com.inc.vr.corp.app.quranwidget.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Arabic::class, Indo::class, Sura::class, SuraNew::class), version = 1)
abstract class AppDB: RoomDatabase() {
    abstract fun arabicDao(): ArabicDao
    abstract fun indoDao(): IndoDao
    abstract fun suraDao(): SuraDao
    abstract fun suraDaoAll(): SuraDaoAll
    abstract fun indoDaoAll(): IndoDaoAll
    abstract fun arabDaoAll(): ArabicDaoAll

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(
                context: Context
        ): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context,
                        AppDB::class.java,
                        "app_database"
                )
                        .createFromAsset("database/DBquran2.db")
                        .allowMainThreadQueries()
                        .build()
                INSTANCE = instance

                instance
            }
        }
    }
}