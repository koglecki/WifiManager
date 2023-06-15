package com.example.zadaniezaliczeniowe

import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.zadaniezaliczeniowe.data.Read
import com.example.zadaniezaliczeniowe.data.ReadDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var wifiManager : WifiManager
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var database: ReadDatabase
    private var locationPermission = false

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(
            applicationContext,
            ReadDatabase::class.java,
            "reads_database"
        ).build()

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            locationPermission
        }
        checkPermission()

        setContentView(R.layout.activity_main)
        val readButton : Button = findViewById(R.id.read)
        val historyButton : Button = findViewById(R.id.history)

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
        val infoText : TextView = findViewById(R.id.info)
        val ssidText : TextView = findViewById(R.id.ssidText)
        val rssiText : TextView = findViewById(R.id.rssiText)
        val linkSpeedText : TextView = findViewById(R.id.link_speedText)
        val frequencyText : TextView = findViewById(R.id.frequencyText)
        val distanceText : TextView = findViewById(R.id.distanceText)
        if (wifiManager.isWifiEnabled) {
            val ssid = wifiInfo.ssid
            val rssi = wifiInfo.rssi
            val linkSpeed = wifiInfo.linkSpeed
            val freq = wifiInfo.frequency
            val distance = distance(rssi, freq)

            val rssiString = rssi.toString() + " dBm"
            val linkSpeedString = linkSpeed.toString() + " Mb/s"
            val freqString = freq.toString() + " MHz"
            val distString = distance + " m"

            infoText.text = "Parametry aktywnego połączenia:"
            ssidText.text = ssid
            rssiText.text = rssiString
            linkSpeedText.text = linkSpeedString
            frequencyText.text = freqString
            distanceText.text = distString
            save(ssid, rssiString, linkSpeedString, freqString, distString)
        }
        else {
            infoText.text = "Brak połączenia"
            ssidText.text = "-"
            rssiText.text = "-"
            linkSpeedText.text = "-"
            frequencyText.text = "-"
            distanceText.text = "-"
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
        val form = DecimalFormat("#.#")
        val dist = form.format(dis)
        return dist
    }

    private fun save(ssid: String, rssi: String, speed: String, frequency: String, distance: String) {
        val read = Read(readSSID=ssid, readRSSI=rssi, readSpeed=speed, readFrequency=frequency, readdistance=distance)
        GlobalScope.launch(Dispatchers.IO) {
            database.readDao().insert(read)
        }
    }

    private fun checkPermission() {
        locationPermission = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        var permissionRequest = false
        if (!locationPermission) {
            permissionRequest = true
        }

        if (permissionRequest) {
            permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

}
