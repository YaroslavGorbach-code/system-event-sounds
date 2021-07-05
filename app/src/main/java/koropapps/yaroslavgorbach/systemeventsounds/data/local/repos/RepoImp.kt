package koropapps.yaroslavgorbach.systemeventsounds.data.local.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

class RepoImp private constructor(context: Context) : Repo {
    private var events = MutableLiveData<List<SystemEvent>>()

    companion object{
        private var instance: Repo? = null
        fun getInstance(context: Context): Repo {
            if (instance == null){
                instance = RepoImp(context)
            }
            return instance as Repo
        }
    }

    init {
        events.value = arrayListOf(
            SystemEvent(
                EventName.WIFI_ON,
                R.drawable.ic_wifi,
                context.getString(R.string.wifi_on_default_text),
                null
            ),
            SystemEvent(
                EventName.WIFI_OFF,
                R.drawable.ic_wifi_off,
                context.getString(R.string.wifi_off_default_text),
                null
            ),
            SystemEvent(
                EventName.MOBILE_INET_ON,
                R.drawable.ic_mobiledata,
                context.getString(R.string.mobile_inet_on_default_text),
                null
            ),
            SystemEvent(
                EventName.MOBILE_INET_OFF,
                R.drawable.ic_mobiledata_off,
                context.getString(R.string.mobile_inet_off_default_text),
                null
            ),
            SystemEvent(
                EventName.AIRPLANE_MOD_ON,
                R.drawable.ic_airplanemode,
                context.getString(R.string.airplane_mod_on_default_text),
                null
            ),
            SystemEvent(
                EventName.AIRPLANE_MOD_OFF,
                R.drawable.ic_airplanemode_inactive,
                context.getString(R.string.airplane_mod_off_default_text),
                null
            ),
            SystemEvent(
                EventName.BLUETOOTH_ON,
                R.drawable.ic_bluetooth,
                context.getString(R.string.bluetooth_on_default_text),
                null
            ),
            SystemEvent(
                EventName.BLUETOOTH_OFF,
                R.drawable.ic_bluetooth_disabled,
                context.getString(R.string.bluetooth_off_default_text),
                null
            ),
            SystemEvent(
                EventName.SCREEN_OFF,
                R.drawable.ic_screen_off,
                context.getString(R.string.screen_off_default_text),
                null
            ),
            SystemEvent(
                EventName.SCREEN_ON,
                R.drawable.ic_screen_on,
                context.getString(R.string.screen_on_default_text),
                null
            ),
            SystemEvent(
                EventName.USB_ATTACHED,
                R.drawable.ic_usb,
                context.getString(R.string.usb_plugged_in_default_text),
                null
            ),
            SystemEvent(
                EventName.USB_DETACHED,
                R.drawable.ic_usb_off,
                context.getString(R.string.usb_plugged_out_default_text),
                null
            ),
            SystemEvent(
                EventName.APP_INSTALLED,
                R.drawable.ic_instoll_app,
                context.getString(R.string.app_installed_default_text),
                null
            ),
            SystemEvent(
                EventName.APP_DELETED,
                R.drawable.ic_delete_app,
                context.getString(R.string.app_deleted_default_text),
                null
            ),
            SystemEvent(
                EventName.HEADPHONES_PLUGGED,
                R.drawable.ic_headphones,
                context.getString(R.string.headphones_plugged_default_text),
                null
            ),
            SystemEvent(
                EventName.HEADPHONES_UNPLUGGED,
                R.drawable.ic_headset_off,
                context.getString(R.string.headphones_unplugged_default_text),
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
        events.value = events.value?.toMutableList()?.apply {
            val index = indexOfFirst { event == it }
            if (index != -1) {
                set(index, event)
            }
        }
    }
}
