package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.bluetooth.BluetoothAdapter
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

class BluetoothChangeReceiver : BroadcastReceiver() {

    @InternalCoroutinesApi
    override fun onReceive(context: Context, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            val getEventUseCase = GetEventUseCase(context.getRepo())
            intent?.let {
                if (intent.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                    val state =
                        intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                    if (state == BluetoothAdapter.STATE_ON
                        && getEventUseCase(EventName.BLUETOOTH_ON).active
                    ) {
                        getEventUseCase(EventName.BLUETOOTH_ON).fileUri?.let { uri ->
                            val playerIntent = Intent(context, MediaPlayerService::class.java)
                            playerIntent.data = uri
                            context.startService(playerIntent)
                        }

                        getEventUseCase(EventName.BLUETOOTH_ON).textToSpeech?.let { text ->
                            val speechIntent = Intent(context, TextToSpeechService::class.java)
                            speechIntent.putExtra("MESSAGE", text)
                            context.startService(speechIntent)
                        }
                    }
                    if (state == BluetoothAdapter.STATE_OFF
                        && getEventUseCase(EventName.BLUETOOTH_OFF).active
                    ) {
                        getEventUseCase(EventName.BLUETOOTH_OFF).fileUri?.let { uri ->
                            val playerIntent = Intent(context, MediaPlayerService::class.java)
                            playerIntent.data = uri
                            context.startService(playerIntent)
                        }

                        getEventUseCase(EventName.BLUETOOTH_OFF).textToSpeech?.let { text ->
                            val speechIntent = Intent(context, TextToSpeechService::class.java)
                            speechIntent.putExtra("MESSAGE", text)
                            context.startService(speechIntent)
                        }
                    }
                }
            }
        }
    }
}