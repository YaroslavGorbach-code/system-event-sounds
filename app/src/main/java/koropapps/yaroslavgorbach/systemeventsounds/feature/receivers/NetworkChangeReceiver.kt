package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.ConsumeEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo


class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val getEventUseCase = GetEventUseCase(getRepo(context))
        val consumeEventUseCase = ConsumeEventUseCase(getRepo(context))

        if (wifi != null
            && wifi.isConnectedOrConnecting
            && !getEventUseCase(EventName.WIFI_ON).consumed
        ) {
            Toast.makeText(context, "WIFI CONNECTED", Toast.LENGTH_LONG).show()
            consumeEventUseCase(EventName.WIFI_ON, true)
            consumeEventUseCase(EventName.WIFI_OFF, false)
        }

        if (wifi != null
            && !wifi.isConnectedOrConnecting
            && !getEventUseCase(EventName.WIFI_OFF).consumed
        ) {
            Toast.makeText(context, "WIFI DISCONNECTED", Toast.LENGTH_LONG).show()
            consumeEventUseCase(EventName.WIFI_OFF, true)
            consumeEventUseCase(EventName.WIFI_ON, false)
        }

        if (mobile != null
            && mobile.isConnectedOrConnecting
            && !getEventUseCase(EventName.MOBILE_INET_ON
            ).consumed
        ) {
            Toast.makeText(context, "MOBILE INET CONNECTED", Toast.LENGTH_LONG).show()
            consumeEventUseCase(EventName.MOBILE_INET_ON, true)
            consumeEventUseCase(EventName.MOBILE_INET_OFF, false)
        }

        if (mobile != null
            && !mobile.isConnectedOrConnecting
            && !getEventUseCase(EventName.MOBILE_INET_OFF).consumed
        ) {
            Toast.makeText(context, "MOBILE INET DISCONNECTED", Toast.LENGTH_LONG).show()
            consumeEventUseCase(EventName.MOBILE_INET_OFF, true)
            consumeEventUseCase(EventName.MOBILE_INET_ON, false)
        }
    }
}