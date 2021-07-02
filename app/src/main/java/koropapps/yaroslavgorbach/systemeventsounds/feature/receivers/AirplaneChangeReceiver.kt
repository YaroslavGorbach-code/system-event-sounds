package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.TextToSpeechService
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo
import kotlinx.coroutines.InternalCoroutinesApi

class AirplaneChangeReceiver : BroadcastReceiver() {

    @InternalCoroutinesApi
    override fun onReceive(context: Context, intent: Intent?) {
        val getEventUseCase = GetEventUseCase(getRepo(context))
        if (intent?.getBooleanExtra("state", false) == true
            && getEventUseCase(EventName.AIRPLANE_MOD_ON).active
        ) {
            getEventUseCase(EventName.AIRPLANE_MOD_ON).textToSpeech?.let {text ->
                val speechIntent = Intent(context, TextToSpeechService::class.java)
                speechIntent.putExtra("MESSAGE", text)
                context.startService(speechIntent)
            }
            Toast.makeText(context, "AIRPLANE MOD ON", Toast.LENGTH_LONG).show()
        }
        if (intent?.getBooleanExtra("state", false) == false
            && getEventUseCase(EventName.AIRPLANE_MOD_OFF).active
        ) {
            getEventUseCase(EventName.AIRPLANE_MOD_OFF).textToSpeech?.let {text ->
                val speechIntent = Intent(context, TextToSpeechService::class.java)
                speechIntent.putExtra("MESSAGE", text)
                context.startService(speechIntent)
            }
            Toast.makeText(context, "AIRPLANE MOD OFF", Toast.LENGTH_LONG).show()
        }
    }
}