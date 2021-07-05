package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MediaPlayerService
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.TextToSpeechService
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo
import kotlinx.coroutines.InternalCoroutinesApi

class UsbConnectionChangeReceiver : BroadcastReceiver() {
    @InternalCoroutinesApi
    override fun onReceive(context: Context, intent: Intent?) {
        val getEventUseCase = GetEventUseCase(context.getRepo())

        intent?.let {
            if (intent.action == Intent.ACTION_POWER_CONNECTED
                && getEventUseCase(EventName.USB_ATTACHED).active
            ) {
                getEventUseCase(EventName.USB_ATTACHED).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.USB_ATTACHED).fileUri?.let { uri->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                Toast.makeText(context, "USB ATTACHED", Toast.LENGTH_LONG).show()
            }
            if (intent.action == Intent.ACTION_POWER_DISCONNECTED
                && getEventUseCase(EventName.USB_DETACHED).active
            ) {
                getEventUseCase(EventName.USB_DETACHED).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.USB_DETACHED).fileUri?.let { uri->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                Toast.makeText(context, "USB DETACHED", Toast.LENGTH_LONG).show()
            }
        }
    }
}