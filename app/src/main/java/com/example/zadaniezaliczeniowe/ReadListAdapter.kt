package com.example.zadaniezaliczeniowe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zadaniezaliczeniowe.data.Read

class ReadListAdapter(private val readList: List<Read>) : RecyclerView.Adapter<ReadListAdapter.ReadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadViewHolder {
        val readView = LayoutInflater.from(parent.context).inflate(R.layout.read_list_read, parent, false)
        return ReadViewHolder(readView)
    }

    override fun onBindViewHolder(holder: ReadViewHolder, position: Int) {
        val currentRead = readList[position]

        // Bind data to views in the ViewHolder
        holder.ssidTextView.text = currentRead.readSSID
        holder.rssiTextView.text = currentRead.readRSSI
        holder.speedTextView.text = currentRead.readSpeed
        holder.freqTextView.text = currentRead.readFrequency
        holder.distTextView.text = currentRead.readdistance
    }

    override fun getItemCount(): Int {
        return readList.size
    }

    class ReadViewHolder(readView: View) : RecyclerView.ViewHolder(readView) {
        // Declare and initialize views in the item layout
        val ssidTextView: TextView = readView.findViewById(R.id.ssid1)
        val rssiTextView: TextView = readView.findViewById(R.id.rssi1)
        val speedTextView: TextView = readView.findViewById(R.id.link_speed1)
        val freqTextView: TextView = readView.findViewById(R.id.frequency1)
        val distTextView: TextView = readView.findViewById(R.id.distance1)
    }
}


