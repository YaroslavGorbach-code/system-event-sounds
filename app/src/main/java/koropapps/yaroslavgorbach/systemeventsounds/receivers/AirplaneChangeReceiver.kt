package koropapps.yaroslavgorbach.systemeventsounds.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneChangeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.getBooleanExtra("state", false) == true){
            Toast.makeText(context, "AIRPLANE MOD ON", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "AIRPLANE MOD OFF", Toast.LENGTH_LONG).show()
        }
    }
}