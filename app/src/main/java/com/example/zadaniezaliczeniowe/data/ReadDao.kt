package com.example.zadaniezaliczeniowe.data

import androidx.room.*

@Dao
interface ReadDao {
    @Query("SELECT * from read ORDER BY id DESC")
    fun getReads(): List<Read>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(read: Read)


    @Query("DELETE from read")
    fun deleteReads()
}