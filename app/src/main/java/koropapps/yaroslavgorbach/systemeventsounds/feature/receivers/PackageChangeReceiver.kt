package koropapps.yaroslavgorbach.systemeventsounds.feature.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo

class PackageChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val getEventUseCase = GetEventUseCase(getRepo(context))
        intent?.let {
            if (intent.action == Intent.ACTION_PACKAGE_ADDED
                && getEventUseCase(EventName.APP_INSTALLED).active
            ) {
                Toast.makeText(context, "APP INSTALLED", Toast.LENGTH_LONG).show()
            }
            if (intent.action == Intent.ACTION_PACKAGE_REMOVED
                && getEventUseCase(EventName.APP_DELETED).active
            ) {
                Toast.makeText(context, "APP REMOVED", Toast.LENGTH_LONG).show()
            }
        }
    }
}
