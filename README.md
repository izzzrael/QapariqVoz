<p align="center">
  <img src="screenshots/icon.png" alt="YapeVoz Banner" width="20%">
</p>

<h1 align="center">YapeVoz🎤</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Android-5.0%2B-00C853?style=for-the-badge&logo=android&logoColor=white" alt="Android Version">
  <img src="https://img.shields.io/badge/Kotlin-100%25-8A05BE?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin Percentage">
  <img src="https://img.shields.io/badge/Utility-Accessibility-purple?style=for-the-badge" alt="Utility Accessibility">
  <img src="https://img.shields.io/badge/Status-Functional-orange?style=for-the-badge" alt="App Status">
</p>

<br>

## 💡 Sobre el Proyecto

**YapeVoz** no es solo un lector de notificaciones; es una herramienta de utilidad y accesibilidad diseñada para mejorar el flujo de trabajo de pequeños y medianos negocios que utilizan Yape.

En un entorno comercial ruidoso o cuando el dueño está ocupado atendiendo, no siempre es posible mirar el celular para confirmar un pago. YapeVoz soluciona esto utilizando el motor de **Text-to-Speech (TTS)** nativo de Android para narrar de forma instantánea y en tiempo real el **nombre y monto** de cada pago recibido, sin necesidad de interrumpir las tareas actuales.


---

## ✨ Características Principales

* ✅ **Narración en Tiempo Real:** Detecta y narra instantáneamente las notificaciones de pagos de Yape.
* 🎧 **Ajustes de Voz Personalizados:** Permite tunear el narrador mediante barras de deslizamiento (**SeekBars**):
    * **Velocidad:** De muy lento a tipo "ardilla" (0.1x a 2.0x).
    * **Tono (Pitch):** De voz grave (varonil) a aguda (femenina).
    * Los ajustes se guardan automáticamente (**SharedPreferences**).
* Test **Test de Narrador:** Un botón dedicado para probar la configuración de voz sin esperar un Yape real.
* Ajuste **Foco de Utilidad:** Diseño centrado en controles de audio, limpio y directo para el dueño del negocio.

---

## 🛠️ Detalles Técnicos

| Componente | Detalle |
| :--- | :--- |
| **Plataforma** | Android Native |
| **Lenguaje** | Kotlin 100% |
| **Mínima API** | API 21 (Android 5.0) |
| **API Recomendada** | API 34+ (Android 14) para gestión avanzada de permisos |
| **Motor de Audio** | TextToSpeech (TTS) Engine Nativo |
| **Persistencia** | SharedPreferences para configuraciones de usuario |
| **Arquitectura** | NotificationListenerService / BroadcastReceiver / MainActivity |

---

## 🔒 Privacidad y Disclaimer

Este proyecto y sus contenidos no están afiliados, financiados, autorizados, respaldados por, ni asociados de ninguna manera con **Yape**, **Banco de Crédito del Perú (BCP)**, ni ninguna de sus filiales.

**Privacidad:** YapeVoz opera localmente en el dispositivo. Solo lee el texto de las notificaciones para convertirlas en audio. No almacena, transmite, ni recopila datos personales o de transacciones fuera del dispositivo del usuario. Es una herramienta de utilidad pura.

---

## 👥 Créditos

Este proyecto es desarrollado por **Israel Huamán** como parte de su formación en el desarrollo de aplicaciones móviles con enfoque profesional.

## 📄 Licencia

Este proyecto está licenciado bajo los términos que se especifican en el archivo `LICENSE`.
