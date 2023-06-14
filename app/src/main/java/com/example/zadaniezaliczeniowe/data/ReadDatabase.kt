package com.example.zadaniezaliczeniowe.data

import android.content.ClipData
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Read::class], version = 1, exportSchema = false)
abstract class ReadDatabase : RoomDatabase() {
    abstract fun readDao(): ReadDao

    companion object {
        @Volatile
        private var INSTANCE: ReadDatabase? = null

        fun getDatabase(context: Context): ReadDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReadDatabase::class.java,
                    "reads_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}