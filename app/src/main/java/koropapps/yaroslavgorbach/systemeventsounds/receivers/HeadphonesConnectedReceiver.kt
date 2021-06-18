package koropapps.yaroslavgorbach.systemeventsounds.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class HeadphonesConnectedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (intent.getIntExtra("state",-1) == 1){
                Toast.makeText(context, "HEADPHONES PLUGGED", Toast.LENGTH_LONG).show()
            }
            if (intent.getIntExtra("state",-1) == 0){
                Toast.makeText(context, "HEADPHONES UNPLUGGED", Toast.LENGTH_LONG).show()
            }
        }
    }
}