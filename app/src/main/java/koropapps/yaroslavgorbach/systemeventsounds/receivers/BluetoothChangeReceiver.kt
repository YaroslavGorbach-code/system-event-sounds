package koropapps.yaroslavgorbach.systemeventsounds.receivers

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BluetoothChangeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (intent.action == BluetoothAdapter.ACTION_STATE_CHANGED){
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                if (state == BluetoothAdapter.STATE_ON){
                    Toast.makeText(context, "BLUETOOTH ON", Toast.LENGTH_LONG).show()
                }
                if (state == BluetoothAdapter.STATE_OFF){
                    Toast.makeText(context, "BLUETOOTH OFF", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}