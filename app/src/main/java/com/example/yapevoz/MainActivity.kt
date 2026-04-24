package com.example.yapevoz

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sbSpeed = findViewById<SeekBar>(R.id.sbSpeed)
        val sbPitch = findViewById<SeekBar>(R.id.sbPitch)
        val btnPrueba = findViewById<Button>(R.id.btnPruebaVoz)
        val btnAjustes = findViewById<Button>(R.id.btnAbrirAjustes)

        val prefs = getSharedPreferences("YapeVozPrefs", Context.MODE_PRIVATE)


        sbSpeed.progress = (prefs.getFloat("voz_speed", 1.0f) * 100).toInt()
        sbPitch.progress = (prefs.getFloat("voz_pitch", 1.0f) * 100).toInt()


        sbSpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val finalVal = if (progress < 10) 0.1f else progress / 100f
                prefs.edit().putFloat("voz_speed", finalVal).apply()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })


        sbPitch.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val finalVal = if (progress < 10) 0.1f else progress / 100f
                prefs.edit().putFloat("voz_pitch", finalVal).apply()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })


        btnPrueba.setOnClickListener {
            val intent = Intent("TEST_VOZ")
            sendBroadcast(intent)
        }

        btnAjustes.setOnClickListener {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }
    }
}