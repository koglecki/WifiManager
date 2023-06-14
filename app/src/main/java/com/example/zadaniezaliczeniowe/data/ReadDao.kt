package com.example.zadaniezaliczeniowe.data

import android.content.ClipData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadDao {
    @Query("SELECT * from read ORDER BY id DESC")
    fun getReads(): List<Read>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(read: Read)


    @Query("DELETE from read")
    fun deleteReads()
}