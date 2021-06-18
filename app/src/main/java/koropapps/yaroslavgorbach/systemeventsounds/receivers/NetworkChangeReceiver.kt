package koropapps.yaroslavgorbach.systemeventsounds.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifi != null && wifi.isConnectedOrConnecting) {
            Toast.makeText(context, "WIFI CONNECTED", Toast.LENGTH_LONG).show()
        }

        if (wifi != null && !wifi.isConnectedOrConnecting) {
            Toast.makeText(context, "WIFI DISCONNECTED", Toast.LENGTH_LONG).show()
        }

        if (mobile != null && mobile.isConnectedOrConnecting) {
            Toast.makeText(context, "MOBILE INET CONNECTED", Toast.LENGTH_LONG).show()
        }

        if (mobile != null && !mobile.isConnectedOrConnecting) {
            Toast.makeText(context, "MOBILE INET DISCONNECTED", Toast.LENGTH_LONG).show()
        }
    }
}