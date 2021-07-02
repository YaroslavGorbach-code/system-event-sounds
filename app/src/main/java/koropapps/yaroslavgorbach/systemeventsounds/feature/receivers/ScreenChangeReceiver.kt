package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo

class ScreenChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val getEventUseCase = GetEventUseCase(getRepo(context))

        intent?.let {
            if (intent.action == Intent.ACTION_SCREEN_ON
                && getEventUseCase(EventName.SCREEN_ON).active) {
                Toast.makeText(context, "SCREEN_ON", Toast.LENGTH_LONG).show()
            }
            if (intent.action == Intent.ACTION_SCREEN_OFF
                && getEventUseCase(EventName.SCREEN_OFF).active) {
                Toast.makeText(context, "SCREEN_OFF", Toast.LENGTH_LONG).show()
            }
        }
    }
}