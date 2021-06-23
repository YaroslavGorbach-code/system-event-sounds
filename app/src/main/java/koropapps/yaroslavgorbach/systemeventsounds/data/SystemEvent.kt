package koropapps.yaroslavgorbach.systemeventsounds.data

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import koropapps.yaroslavgorbach.systemeventsounds.R

enum class EventName(@StringRes val idRes: Int) {
    WIFI_ON(R.string.wifi_on),
    WIFI_OFF(R.string.wifi_off),
    MOBILE_INET_ON(R.string.mobile_inet_on),
    MOBILE_INET_OFF(R.string.mobile_inet_off),
    AIRPLANE_MOD_ON(R.string.airplane_mod_on),
    AIRPLANE_MOD_OFF(R.string.airplane_mod_off),
    BLUETOOTH_ON(R.string.bluetooth_on),
    BLUETOOTH_OFF(R.string.bluetooth_off),
    SCREEN_ON(R.string.screen_on),
    SCREEN_OFF(R.string.screen_off),
    USB_ATTACHED(R.string.usb_attached),
    USB_DETACHED(R.string.usb_detached),
    APP_INSTALLED(R.string.app_installed),
    APP_DELETED(R.string.app_deleted),
    HEADPHONES_PLUGGED(R.string.headphones_plugged),
    HEADPHONES_UNPLUGGED(R.string.headphones_unplugged),
}

data class SystemEvent(
    val name: EventName,
    @DrawableRes val imageId: Int,
    val textToSpeech: String?,
    val fileUri: Uri?,
    val active: Boolean = false,
    var consumed: Boolean = false
)