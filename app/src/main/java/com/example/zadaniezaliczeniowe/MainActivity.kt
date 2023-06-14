package com.example.zadaniezaliczeniowe

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.zadaniezaliczeniowe.data.Read
import com.example.zadaniezaliczeniowe.data.ReadDatabase
import com.example.zadaniezaliczeniowe.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var wifiManager : WifiManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var database: ReadDatabase

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            ReadDatabase::class.java,
            "reads_database"
        ).build()

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.) as NavHostFragment
//        navController = navHostFragment.navController
//
//        setupActionBarWithNavController(navController)
//////////////////////////////////////////////////////////////////
        setContentView(R.layout.activity_main)
        val readButton : Button = findViewById(R.id.read)
        val historyButton : Button = findViewById(R.id.history)

        // Register the permissions callback, which handles the user's response to the
// system permissions dialog. Save the return value, an instance of
// ActivityResultLauncher. You can use either a val, as shown in this snippet,
// or a lateinit var in your onAttach() or onCreate() method.
        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }


        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager

        readButton.setOnClickListener {
            refresh()
        }
        refresh()

        historyButton.setOnClickListener {
            val intent = Intent(this, ReadListActivity::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun refresh() {
        val wifiInfo: WifiInfo = wifiManager.connectionInfo  //deprecated
        val textPole : TextView = findViewById(R.id.test1)
        val textPole2 : TextView = findViewById(R.id.ssid)
        val textPole3 : TextView = findViewById(R.id.rssi)
        val textPole4 : TextView = findViewById(R.id.link_speed)
        val textPole5 : TextView = findViewById(R.id.frequency)
        val textPole6 : TextView = findViewById(R.id.distance)
        if (wifiManager.isWifiEnabled) {
            val ssid = wifiInfo.ssid
            val rssi = wifiInfo.rssi
            val link_speed = wifiInfo.linkSpeed
            val freq = wifiInfo.frequency
            textPole.text = ""
            textPole2.text = "SSID: " + ssid
            textPole3.text = "RSSI: " + rssi.toString()
            textPole4.text = "Link speed: " + link_speed.toString()
            textPole5.text = "Frequency: " + freq
            textPole6.text = "Distance: " + distance(rssi, freq) + " m"
            save(ssid.toString(), rssi.toString(), link_speed.toString(), freq.toString(), distance(rssi, freq))
        }
        else {
            val textPole : TextView = findViewById(R.id.test1)
            textPole.text = "brak połączenia"
            textPole2.text = "SSID: -"
            textPole3.text = "RSSI: -"
            textPole4.text = "Link speed: -"
            textPole5.text = "Frequency: -"
            textPole6.text = "Distance: -"
        }
    }

    private fun distance(rssi : Int, frequency : Int): String {     //One-Slope
        val Lc = 20 - rssi
        val gamma = 3.5
        val f = frequency.toDouble()
        //-27.55 + 20 * np.log10(f) + 20 * np.log10(d) + 10 * gamma * np.log(d) - Lc = 0
        //-27.55 + 20 * np.log10(f) + np.log10(d) * (20 + 10 * gamma) - Lc = 0
        //27.55 - 20 * np.log10(f) + Lc = np.log10(d) * (20 + 10 * gamma)
        //np.log10(d) = (27.55 - 20 * np.log10(f) + Lc) / (20 + 10 * gamma)
        //val dist = Math.pow(10.0, ((27.55 - 20 * np.log10(f) + Lc) / (20 + 10 * gamma)))
        val dis = Math.pow(10.0, ((27.55 - 20 * Math.log10(f) + Lc) / (20 + 10 * gamma)))
//        val dist = d.toBigDecimal()
//        dist.format
        val form = DecimalFormat("#.#")
        val dist = form.format(dis)
        return dist
    }

    private fun save(ssid: String, rssi: String, speed: String, frequency: String, distance: String) {
        val read = Read(readSSID=ssid, readRSSI=rssi, readSpeed=speed, readFrequency=frequency, distance=distance)
        GlobalScope.launch(Dispatchers.IO) {
            database.readDao().insert(read)
        }
    }

    private fun readData() {

    }

}
