package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.data.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.getRepo


class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifi != null && wifi.isConnectedOrConnecting && !getRepo(context).getEvent(EventName.WIFI_ON).consumed) {
            Toast.makeText(context, "WIFI CONNECTED", Toast.LENGTH_LONG).show()
            getRepo(context).consumeEvent(EventName.WIFI_ON, true)
            getRepo(context).consumeEvent(EventName.WIFI_OFF, false)
        }

        if (wifi != null && !wifi.isConnectedOrConnecting && !getRepo(context).getEvent(EventName.WIFI_OFF).consumed) {
            Toast.makeText(context, "WIFI DISCONNECTED", Toast.LENGTH_LONG).show()
            getRepo(context).consumeEvent(EventName.WIFI_OFF, true)
            getRepo(context).consumeEvent(EventName.WIFI_ON, false)
        }

        if (mobile != null && mobile.isConnectedOrConnecting  && !getRepo(context).getEvent(EventName.MOBILE_INET_ON).consumed) {
            Toast.makeText(context, "MOBILE INET CONNECTED", Toast.LENGTH_LONG).show()
            getRepo(context).consumeEvent(EventName.MOBILE_INET_ON, true)
            getRepo(context).consumeEvent(EventName.MOBILE_INET_OFF, false)
        }

        if (mobile != null && !mobile.isConnectedOrConnecting && !getRepo(context).getEvent(EventName.MOBILE_INET_OFF).consumed) {
            Toast.makeText(context, "MOBILE INET DISCONNECTED", Toast.LENGTH_LONG).show()
            getRepo(context).consumeEvent(EventName.MOBILE_INET_OFF, true)
            getRepo(context).consumeEvent(EventName.MOBILE_INET_ON, false)
        }
    }
}