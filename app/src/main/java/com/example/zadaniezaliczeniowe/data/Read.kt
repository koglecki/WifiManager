package com.example.zadaniezaliczeniowe.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Read(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "SSID")
    val readSSID: String,
    @ColumnInfo(name = "RSSI")
    val readRSSI: String,
    @ColumnInfo(name = "linkSpeed")
    val readSpeed: String,
    @ColumnInfo(name = "frequency")
    val readFrequency: String,
    @ColumnInfo(name = "distance")
    val readdistance: String,
)