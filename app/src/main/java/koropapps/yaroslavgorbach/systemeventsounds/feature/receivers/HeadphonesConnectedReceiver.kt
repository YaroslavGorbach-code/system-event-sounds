package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.ConsumeEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo

class HeadphonesConnectedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val consumeEventUseCase = ConsumeEventUseCase(getRepo(context))
        val getEventUseCase = GetEventUseCase(getRepo(context))
        intent?.let {
            if (intent.getIntExtra("state", -1) == 1) {
                Toast.makeText(context, "HEADPHONES PLUGGED", Toast.LENGTH_LONG).show()
                consumeEventUseCase(EventName.HEADPHONES_UNPLUGGED, false)
            }
            if (intent.getIntExtra("state", -1) == 0
                && getEventUseCase(EventName.HEADPHONES_UNPLUGGED).consumed
            ) {
                Toast.makeText(context, "HEADPHONES UNPLUGGED", Toast.LENGTH_LONG).show()
                consumeEventUseCase(EventName.HEADPHONES_UNPLUGGED, true)
            }
        }
    }
}