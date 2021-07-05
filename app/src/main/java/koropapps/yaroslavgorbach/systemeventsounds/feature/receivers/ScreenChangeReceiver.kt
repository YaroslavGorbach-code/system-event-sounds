package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MediaPlayerService
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.TextToSpeechService
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class ScreenChangeReceiver : BroadcastReceiver() {
    @InternalCoroutinesApi
    override fun onReceive(context: Context, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            val getEventUseCase = GetEventUseCase(context.getRepo())
            intent?.let {
                if (intent.action == Intent.ACTION_SCREEN_ON
                    && getEventUseCase(EventName.SCREEN_ON).active
                ) {
                    getEventUseCase(EventName.SCREEN_ON).textToSpeech?.let { text ->
                        val speechIntent = Intent(context, TextToSpeechService::class.java)
                        speechIntent.putExtra("MESSAGE", text)
                        context.startService(speechIntent)
                    }

                    getEventUseCase(EventName.SCREEN_ON).fileUri?.let { uri ->
                        val playerIntent = Intent(context, MediaPlayerService::class.java)
                        playerIntent.data = uri
                        context.startService(playerIntent)
                    }
                }
                if (intent.action == Intent.ACTION_SCREEN_OFF
                    && getEventUseCase(EventName.SCREEN_OFF).active
                ) {
                    getEventUseCase(EventName.SCREEN_OFF).textToSpeech?.let { text ->
                        val speechIntent = Intent(context, TextToSpeechService::class.java)
                        speechIntent.putExtra("MESSAGE", text)
                        context.startService(speechIntent)
                    }

                    getEventUseCase(EventName.SCREEN_OFF).fileUri?.let { uri ->
                        val playerIntent = Intent(context, MediaPlayerService::class.java)
                        playerIntent.data = uri
                        context.startService(playerIntent)
                    }
                }
            }
        }
    }
}