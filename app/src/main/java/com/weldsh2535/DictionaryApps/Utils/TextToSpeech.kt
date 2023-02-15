package com.weldsh2535.DictionaryApps.Utils

import android.app.Activity
import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import java.util.Locale

class TextToSpeech(
    private val activity: Context,
    private val message: String,
    private var br: Boolean
) : TextToSpeech.OnInitListener {
    private val tts: TextToSpeech = TextToSpeech(activity, this)

    override fun onInit(i: Int) {
        if (i == TextToSpeech.SUCCESS) {
            val localBR = Locale("en", "US")
            val localeUS = Locale.US

            br = true
            val result: Int = tts.setLanguage(localBR)
            tts.setLanguage(localeUS)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(activity, "This Language is not supported", Toast.LENGTH_SHORT)
                    .show()
            } else {
                speakOut(message)
            }
        } else {
            br = false
        }
    }

    private fun speakOut(message: String) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
    }

}