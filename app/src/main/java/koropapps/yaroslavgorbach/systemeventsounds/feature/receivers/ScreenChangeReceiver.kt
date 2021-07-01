package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ScreenChangeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (intent.action == Intent.ACTION_SCREEN_ON){
                Toast.makeText(context, "SCREEN_ON", Toast.LENGTH_LONG).show()
            }
            if (intent.action == Intent.ACTION_SCREEN_OFF){
                Toast.makeText(context, "SCREEN_OFF", Toast.LENGTH_LONG).show()
            }
        }
    }
}