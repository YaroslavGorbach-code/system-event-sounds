package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo

class BluetoothChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val getEventUseCase = GetEventUseCase(getRepo(context))
        intent?.let {
            if (intent.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                val state =
                    intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                if (state == BluetoothAdapter.STATE_ON
                    && getEventUseCase(EventName.BLUETOOTH_ON).active
                ) {
                    Toast.makeText(context, "BLUETOOTH ON", Toast.LENGTH_LONG).show()
                }
                if (state == BluetoothAdapter.STATE_OFF
                    && getEventUseCase(EventName.BLUETOOTH_OFF).active
                ) {
                    Toast.makeText(context, "BLUETOOTH OFF", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}