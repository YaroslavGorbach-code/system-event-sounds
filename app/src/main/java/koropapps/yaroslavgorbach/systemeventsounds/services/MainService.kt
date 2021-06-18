package koropapps.yaroslavgorbach.systemeventsounds.services

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.IBinder
import koropapps.yaroslavgorbach.systemeventsounds.receivers.*
import koropapps.yaroslavgorbach.systemeventsounds.util.getServiceNotification
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainService : Service() {
    private val networkChangeReceiver: BroadcastReceiver = NetworkChangeReceiver()
    private val airplaneChangeReceiver: BroadcastReceiver = AirplaneChangeReceiver()
    private val bluetoothChangeReceiver: BroadcastReceiver = BluetoothChangeReceiver()
    private val screenChangeReceiver: BroadcastReceiver = ScreenChangeReceiver()
    private val usbConnectionChangeReceiver: BroadcastReceiver = UsbConnectionChangeReceiver()
    private val packageChangeReceiver: BroadcastReceiver = PackageChangeReceiver()
    private val headphonesConnectedReceiver: BroadcastReceiver = HeadphonesConnectedReceiver()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        registerReceiver(
            networkChangeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        registerReceiver(
            airplaneChangeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
        registerReceiver(
            bluetoothChangeReceiver,
            IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        )
        registerReceiver(
            screenChangeReceiver,
            IntentFilter().apply {
                addAction(Intent.ACTION_SCREEN_ON)
                addAction(Intent.ACTION_SCREEN_OFF)
            }
        )
        registerReceiver(
            usbConnectionChangeReceiver,
            IntentFilter().apply {
                addAction(Intent.ACTION_POWER_CONNECTED)
                addAction(Intent.ACTION_POWER_DISCONNECTED)
            }
        )
        registerReceiver(
            packageChangeReceiver,
            IntentFilter().apply {
                addAction(Intent.ACTION_PACKAGE_ADDED)
                addAction(Intent.ACTION_PACKAGE_REMOVED)
                addDataScheme("package")
            }
        )
        registerReceiver(
            headphonesConnectedReceiver,
            IntentFilter(Intent.ACTION_HEADSET_PLUG)
        )
        startForeground(1, this.getServiceNotification())
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
        unregisterReceiver(airplaneChangeReceiver)
        unregisterReceiver(bluetoothChangeReceiver)
        unregisterReceiver(screenChangeReceiver)
        unregisterReceiver(usbConnectionChangeReceiver)
        unregisterReceiver(packageChangeReceiver)
        unregisterReceiver(headphonesConnectedReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}