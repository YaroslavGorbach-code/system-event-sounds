package koropapps.yaroslavgorbach.systemeventsounds.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PackageChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (intent.action == Intent.ACTION_PACKAGE_ADDED) {
                Toast.makeText(context, "APP INSTALLED", Toast.LENGTH_LONG).show()
            }
            if (intent.action == Intent.ACTION_PACKAGE_REMOVED) {
                Toast.makeText(context, "APP REMOVED", Toast.LENGTH_LONG).show()
            }
        }
    }
}