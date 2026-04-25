package com.example.qapariqvoz

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.speech.tts.TextToSpeech
import java.util.Locale

class YapeService : NotificationListenerService(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    private val testReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            hablar("¡Cuchau! QapariqVoz está configurado correctamente.")
        }
    }

    override fun onCreate() {
        super.onCreate()
        tts = TextToSpeech(this, this)

        val filter = IntentFilter("TEST_VOZ")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(testReceiver, filter, RECEIVER_EXPORTED)
        } else {
            registerReceiver(testReceiver, filter)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale("es", "PE")
        }
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val packageName = sbn?.packageName
        if (packageName == "com.bcp.innovatcx.yape") { // Filtro para Yape
            val extras = sbn.notification.extras
            val titulo = extras.getString("android.title") ?: ""
            val texto = extras.getString("android.text") ?: ""

            // Regex para captar monto y nombre
            val regexMonto = "([0-9]+[.][0-9]{2})".toRegex()
            val matchMonto = regexMonto.find(texto)
            val monto = matchMonto?.value ?: ""

            if (monto.isNotEmpty()) {
                hablar("Pago recibido de $titulo por un monto de $monto soles")
            }
        }
    }

    private fun hablar(mensaje: String) {
        val prefs = getSharedPreferences("YapeVozPrefs", Context.MODE_PRIVATE)
        val speed = prefs.getFloat("voz_speed", 1.0f)
        val pitch = prefs.getFloat("voz_pitch", 1.0f)

        tts?.setSpeechRate(speed)
        tts?.setPitch(pitch)

        tts?.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, "ID_QAPARIQ")
    }

    override fun onDestroy() {
        super.onDestroy()
        tts?.stop()
        tts?.shutdown()
        unregisterReceiver(testReceiver)
    }
}
}