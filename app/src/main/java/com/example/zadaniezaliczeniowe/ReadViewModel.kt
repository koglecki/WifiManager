package com.example.zadaniezaliczeniowe

import android.content.ClipData

import androidx.lifecycle.*
import com.example.zadaniezaliczeniowe.data.Read
import com.example.zadaniezaliczeniowe.data.ReadDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class ReadViewModel(private val readDao: ReadDao) : ViewModel() {
    fun allReads(): List<Read> = readDao.getReads()
}

class ReadViewModelFactory(private val readDao : ReadDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReadViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReadViewModel(readDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
