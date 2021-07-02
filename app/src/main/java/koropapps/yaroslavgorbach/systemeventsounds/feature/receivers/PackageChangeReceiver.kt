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

class PackageChangeReceiver : BroadcastReceiver() {

    @InternalCoroutinesApi
    override fun onReceive(context: Context, intent: Intent?) {
        val getEventUseCase = GetEventUseCase(getRepo(context))
        intent?.let {
            if (intent.action == Intent.ACTION_PACKAGE_ADDED
                && getEventUseCase(EventName.APP_INSTALLED).active
            ) {
                getEventUseCase(EventName.APP_INSTALLED).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.APP_INSTALLED).fileUri?.let { uri->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                Toast.makeText(context, "APP INSTALLED", Toast.LENGTH_LONG).show()
            }
            if (intent.action == Intent.ACTION_PACKAGE_REMOVED
                && getEventUseCase(EventName.APP_DELETED).active
            ) {
                getEventUseCase(EventName.APP_DELETED).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.APP_DELETED).fileUri?.let { uri->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                Toast.makeText(context, "APP REMOVED", Toast.LENGTH_LONG).show()
            }
        }
    }
}
