package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class UsbConnectionChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.let {
            if (intent.action == Intent.ACTION_POWER_CONNECTED) {
                Toast.makeText(context, "USB ATTACHED", Toast.LENGTH_LONG).show()
            }
            if (intent.action == Intent.ACTION_POWER_DISCONNECTED){
                Toast.makeText(context, "USB DETACHED", Toast.LENGTH_LONG).show()
            }
        }
    }
}