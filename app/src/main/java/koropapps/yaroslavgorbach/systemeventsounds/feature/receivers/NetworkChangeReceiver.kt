package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.ConsumeEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MediaPlayerService
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.TextToSpeechService
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class NetworkChangeReceiver : BroadcastReceiver() {
    @InternalCoroutinesApi
    override fun onReceive(context: Context, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            val connMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            val getEventUseCase = GetEventUseCase(context.getRepo())
            val consumeEventUseCase = ConsumeEventUseCase(context.getRepo())

            if (wifi != null
                && wifi.isConnectedOrConnecting
                && !getEventUseCase(EventName.WIFI_ON).consumed
                && getEventUseCase(EventName.WIFI_ON).active
            ) {
                getEventUseCase(EventName.WIFI_ON).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.WIFI_ON).fileUri?.let { uri ->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                consumeEventUseCase(EventName.WIFI_ON, true)
                consumeEventUseCase(EventName.WIFI_OFF, false)
            }

            if (wifi != null
                && !wifi.isConnectedOrConnecting
                && !getEventUseCase(EventName.WIFI_OFF).consumed
                && getEventUseCase(EventName.WIFI_OFF).active
            ) {
                getEventUseCase(EventName.WIFI_OFF).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.WIFI_OFF).fileUri?.let { uri ->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                consumeEventUseCase(EventName.WIFI_OFF, true)
                consumeEventUseCase(EventName.WIFI_ON, false)
            }

            if (mobile != null
                && mobile.isConnectedOrConnecting
                && !getEventUseCase(EventName.MOBILE_INET_ON).consumed
                && getEventUseCase(EventName.MOBILE_INET_ON).active
            ) {
                getEventUseCase(EventName.MOBILE_INET_ON).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.MOBILE_INET_ON).fileUri?.let { uri ->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                consumeEventUseCase(EventName.MOBILE_INET_ON, true)
                consumeEventUseCase(EventName.MOBILE_INET_OFF, false)
            }

            if (mobile != null
                && !mobile.isConnectedOrConnecting
                && !getEventUseCase(EventName.MOBILE_INET_OFF).consumed
                && getEventUseCase(EventName.MOBILE_INET_OFF).active
            ) {
                getEventUseCase(EventName.MOBILE_INET_OFF).textToSpeech?.let { text ->
                    val speechIntent = Intent(context, TextToSpeechService::class.java)
                    speechIntent.putExtra("MESSAGE", text)
                    context.startService(speechIntent)
                }

                getEventUseCase(EventName.MOBILE_INET_OFF).fileUri?.let { uri ->
                    val playerIntent = Intent(context, MediaPlayerService::class.java)
                    playerIntent.data = uri
                    context.startService(playerIntent)
                }
                consumeEventUseCase(EventName.MOBILE_INET_OFF, true)
                consumeEventUseCase(EventName.MOBILE_INET_ON, false)
            }
        }
    }

}