package com.example.zadaniezaliczeniowe

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zadaniezaliczeniowe.data.ReadDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReadListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var database: ReadDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_list_activity)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        database = ReadDatabase.getDatabase(applicationContext)

        lifecycleScope.launch {
            show()
        }

        val deleteButton : Button = findViewById(R.id.delete)
        deleteButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                database.readDao().deleteReads()
            }
        }
    }

    private suspend fun show() {
        withContext(Dispatchers.IO) {
            val readList = database.readDao()
                .getReads()
            val adapter = ReadListAdapter(readList)
            recyclerView.adapter = adapter
        }
    }

}
