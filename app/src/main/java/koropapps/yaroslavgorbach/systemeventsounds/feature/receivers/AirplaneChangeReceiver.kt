package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MediaPlayerService
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.TextToSpeechService
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo
import kotlinx.coroutines.InternalCoroutinesApi

class AirplaneChangeReceiver : BroadcastReceiver() {

    @InternalCoroutinesApi
    override fun onReceive(context: Context, intent: Intent?) {
        val getEventUseCase = GetEventUseCase(context.getRepo())
        if (intent?.getBooleanExtra("state", false) == true
            && getEventUseCase(EventName.AIRPLANE_MOD_ON).active
        ) {
            getEventUseCase(EventName.AIRPLANE_MOD_ON).textToSpeech?.let {text ->
                val speechIntent = Intent(context, TextToSpeechService::class.java)
                speechIntent.putExtra("MESSAGE", text)
                context.startService(speechIntent)
            }

            getEventUseCase(EventName.AIRPLANE_MOD_ON).fileUri?.let { uri->
                val playerIntent = Intent(context, MediaPlayerService::class.java)
                playerIntent.data = uri
                context.startService(playerIntent)
            }
            Toast.makeText(context, "AIRPLANE MOD ON", Toast.LENGTH_LONG).show()
        }
        if (intent?.getBooleanExtra("state", false) == false
            && getEventUseCase(EventName.AIRPLANE_MOD_OFF).active
        ) {
            getEventUseCase(EventName.AIRPLANE_MOD_OFF).fileUri?.let { uri->
                val playerIntent = Intent(context, MediaPlayerService::class.java)
                playerIntent.data = uri
                context.startService(playerIntent)
            }

            getEventUseCase(EventName.AIRPLANE_MOD_OFF).textToSpeech?.let {text ->
                val speechIntent = Intent(context, TextToSpeechService::class.java)
                speechIntent.putExtra("MESSAGE", text)
                context.startService(speechIntent)
            }
            Toast.makeText(context, "AIRPLANE MOD OFF", Toast.LENGTH_LONG).show()
        }
    }
}