package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.ConsumeEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MediaPlayerService
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.TextToSpeechService
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo
import kotlinx.coroutines.InternalCoroutinesApi

class HeadphonesConnectedReceiver : BroadcastReceiver() {

    @InternalCoroutinesApi
    override fun onReceive(context: Context, intent: Intent?) {
        val consumeEventUseCase = ConsumeEventUseCase(getRepo(context))
        val getEventUseCase = GetEventUseCase(getRepo(context))
        intent?.let {
            if (intent.getIntExtra("state", -1) == 1
                && getEventUseCase(EventName.HEADPHONES_PLUGGED).active
            ) {
                getEventUseCase(EventName.HEADPHONES_PLUGGED).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.HEADPHONES_PLUGGED).fileUri?.let { uri->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                Toast.makeText(context, "HEADPHONES PLUGGED", Toast.LENGTH_LONG).show()
                consumeEventUseCase(EventName.HEADPHONES_UNPLUGGED, false)
            }
            if (intent.getIntExtra("state", -1) == 0
                && getEventUseCase(EventName.HEADPHONES_UNPLUGGED).consumed
                && getEventUseCase(EventName.HEADPHONES_UNPLUGGED).active
            ) {
                getEventUseCase(EventName.HEADPHONES_UNPLUGGED).fileUri?.let { uri->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }

                getEventUseCase(EventName.HEADPHONES_UNPLUGGED).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }
                Toast.makeText(context, "HEADPHONES UNPLUGGED", Toast.LENGTH_LONG).show()
                consumeEventUseCase(EventName.HEADPHONES_UNPLUGGED, true)
            }
        }
    }
}