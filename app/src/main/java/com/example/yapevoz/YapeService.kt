package com.example.yapevoz

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class YapeService : NotificationListenerService(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private val tagLog = "YAPE_LOG"

    override fun onCreate() {
        super.onCreate()
        Log.d(tagLog, "1. 🚀 Servicio iniciado")
        tts = TextToSpeech(this, this)
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d(tagLog, "2. ✅ Conexión establecida con el sistema")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale("es", "PE")

            // Configuración de voz pausada y varonil
            tts?.setSpeechRate(0.50f)  // Velocidad tranquila
            tts?.setPitch(0.80f)      // Tono más grave

            Log.d(tagLog, "3. 🎙️ Voz lista y configurada")
        }
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val paquete = sbn.packageName
        val extras = sbn.notification.extras
        val titulo = extras.getString("android.title") ?: ""
        val texto = extras.getString("android.text") ?: ""

        Log.d(tagLog, "🔔 NOTIFICACIÓN: [$paquete] | Título: $titulo | Texto: $texto")

        // Filtro para Yape
        if (paquete.contains("yape") || titulo.contains("Pago", ignoreCase = true)) {

            val regexMonto = "S/\\s*([0-9.]+)".toRegex()
            val matchMonto = regexMonto.find(texto)

            if (matchMonto != null) {
                val monto = matchMonto.groupValues[1]
                val nombre = texto.split(" te envió")[0]

                // 1. Que el celular hable
                val mensajeParaHablar = "$nombre te envió $monto soles"
                hablar(mensajeParaHablar)

                // 2. "EL GRITO" (Broadcast): Enviamos los datos a la MainActivity
                val intent = Intent("ACCION_NUEVO_YAPE")
                intent.putExtra("monto", monto)
                intent.putExtra("nombre", nombre)
                sendBroadcast(intent)

                Log.d(tagLog, "📡 Broadcast enviado: $nombre - S/ $monto")

            } else {
                // Si no detecta el monto, lee el texto completo por seguridad
                hablar("$titulo. $texto")
            }
        }
    }

    private fun hablar(mensaje: String) {
        Log.d(tagLog, "📢 HABLANDO: $mensaje")
        tts?.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, "ID")
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}