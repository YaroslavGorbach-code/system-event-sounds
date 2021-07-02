package koropapps.yaroslavgorbach.systemeventsounds.data.local.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

object RepoImp : Repo {
    private var events = MutableLiveData<List<SystemEvent>>()

    init {
        events.value = arrayListOf(
            SystemEvent(
                EventName.WIFI_ON,
                R.drawable.ic_wifi,
                null,
                null
            ),
            SystemEvent(
                EventName.WIFI_OFF,
                R.drawable.ic_wifi_off,
                null,
                null,
                consumed = true
            ),
            SystemEvent(
                EventName.MOBILE_INET_ON,
                R.drawable.ic_mobiledata,
                null,
                null
            ),
            SystemEvent(
                EventName.MOBILE_INET_OFF,
                R.drawable.ic_mobiledata_off,
                null,
                null,
                consumed = true
            ),
            SystemEvent(
                EventName.AIRPLANE_MOD_ON,
                R.drawable.ic_airplanemode,
                null,
                null
            ),
            SystemEvent(
                EventName.AIRPLANE_MOD_OFF,
                R.drawable.ic_airplanemode_inactive,
                null,
                null
            ),
            SystemEvent(
                EventName.BLUETOOTH_ON,
                R.drawable.ic_bluetooth,
                null,
                null
            ),
            SystemEvent(
                EventName.BLUETOOTH_OFF,
                R.drawable.ic_bluetooth_disabled,
                null,
                null
            ),
            SystemEvent(
                EventName.SCREEN_OFF,
                R.drawable.ic_screen_off,
                null,
                null
            ),
            SystemEvent(
                EventName.SCREEN_ON,
                R.drawable.ic_screen_on,
                null,
                null
            ),
            SystemEvent(
                EventName.USB_ATTACHED,
                R.drawable.ic_usb,
                null,
                null
            ),
            SystemEvent(
                EventName.USB_DETACHED,
                R.drawable.ic_usb_off,
                null,
                null
            ),
            SystemEvent(
                EventName.APP_INSTALLED,
                R.drawable.ic_instoll_app,
                null,
                null
            ),
            SystemEvent(
                EventName.APP_DELETED,
                R.drawable.ic_delete_app,
                null,
                null
            ),
            SystemEvent(
                EventName.HEADPHONES_PLUGGED,
                R.drawable.ic_headphones,
                null,
                null
            ),
            SystemEvent(
                EventName.HEADPHONES_UNPLUGGED,
                R.drawable.ic_headset_off,
                null,
                null,
                consumed = true
            ),
        )
    }

    override fun getEvents(): LiveData<List<SystemEvent>> {
        return events
    }

    override fun getEvent(name: EventName): SystemEvent {
        return events.value?.find { it.name == name }!!
    }

    override fun updateEvent(event: SystemEvent) {
        Log.v("event", "set event update")
        events.value = events.value?.toMutableList()?.apply {
            val index = indexOfFirst { event == it }
            if (index != -1) {
                set(index, event)
            }
        }
    }
}
