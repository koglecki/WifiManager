package com.example.zadaniezaliczeniowe


import android.content.ClipData
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zadaniezaliczeniowe.data.Read
import com.example.zadaniezaliczeniowe.databinding.ReadListReadBinding

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
        // Bind more data if needed
    }

    override fun getItemCount(): Int {
        return readList.size
    }
//    class ReadViewHolder(private var binding: ReadListReadBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(read: Read) {
//            binding.apply {
//                ssid.text = read.readSSID
//                rssi.text = read.readRSSI
//                linkSpeed.text = read.readSpeed
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadViewHolder {
//        return ReadViewHolder(
//            ReadListReadBinding.inflate(
//                LayoutInflater.from(
//                    parent.context
//                )
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: ReadViewHolder, position: Int) {
//        holder.ssid
//    }
//
//    companion object {
//        private val DiffCallback = object : DiffUtil.ItemCallback<Read>() {
//            override fun areItemsTheSame(oldItem: Read, newItem: Read): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//            override fun areContentsTheSame(oldItem: Read, newItem: Read): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
class ReadViewHolder(readView: View) : RecyclerView.ViewHolder(readView) {
    // Declare and initialize views in the item layout
    val ssidTextView: TextView = readView.findViewById(R.id.ssid1)
    val rssiTextView: TextView = readView.findViewById(R.id.rssi1)
    // Add more views if needed
}
}


