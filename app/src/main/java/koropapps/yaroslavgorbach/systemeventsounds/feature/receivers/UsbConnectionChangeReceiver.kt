package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo

class UsbConnectionChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val getEventUseCase = GetEventUseCase(getRepo(context))

        intent?.let {
            if (intent.action == Intent.ACTION_POWER_CONNECTED
                && getEventUseCase(EventName.USB_ATTACHED).active
            ) {
                Toast.makeText(context, "USB ATTACHED", Toast.LENGTH_LONG).show()
            }
            if (intent.action == Intent.ACTION_POWER_DISCONNECTED
                && getEventUseCase(EventName.USB_DETACHED).active
            ) {
                Toast.makeText(context, "USB DETACHED", Toast.LENGTH_LONG).show()
            }
        }
    }
}