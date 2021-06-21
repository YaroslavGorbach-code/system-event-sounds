package koropapps.yaroslavgorbach.systemeventsounds.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.data.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.getRepo

class HeadphonesConnectedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (intent.getIntExtra("state",-1) == 1){
                Toast.makeText(context, "HEADPHONES PLUGGED", Toast.LENGTH_LONG).show()
                getRepo(context).consumeEvent(EventName.HEADPHONES_UNPLUGGED, false)
            }
            if (intent.getIntExtra("state",-1) == 0 && !getRepo(context).getEvent(EventName.HEADPHONES_UNPLUGGED).consumed){
                Toast.makeText(context, "HEADPHONES UNPLUGGED", Toast.LENGTH_LONG).show()
                getRepo(context).consumeEvent(EventName.HEADPHONES_UNPLUGGED, true)
            }
        }
    }
}