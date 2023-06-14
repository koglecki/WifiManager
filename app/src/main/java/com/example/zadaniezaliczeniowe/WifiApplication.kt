package com.example.zadaniezaliczeniowe

import android.app.Application
import com.example.zadaniezaliczeniowe.data.ReadDatabase

class WifiApplication : Application(){
    val database: ReadDatabase by lazy { ReadDatabase.getDatabase(this) }
}